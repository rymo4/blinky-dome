package com.github.starcats.blinkydome;

import com.github.starcats.blinkydome.configuration.StarcatsLxConfig;
import heronarts.lx.LX;
import heronarts.lx.color.LXPalette;
import heronarts.lx.output.LXOutput;
import processing.core.PApplet;

import java.io.File;
import java.util.Optional;

/**
 * Headless app that runs some model/configuration
 *
 * (Technically has a PApplet gui to get processing dependencies, but rendered without any contents to minimize
 * rendering time)
 */
public class AppHeadless extends PApplet {

  private static boolean isVerbose = false;

  static public void main(String args[]) {
    PApplet.main(new String[] { "com.github.starcats.blinkydome.AppHeadless" });

    for (String s: args) {
      if (s.equalsIgnoreCase("-v")) {
        isVerbose = true;
      }
    }
  }

  private LX lx;

  public void settings() {
    size(1, 1, P2D); // P2D to minimize any rendering time
  }

  public void setup() {

    PApplet.println("Starting 'headless' blinky-os");

    AppHeadless p = this; // PApplet reference

    final StarcatsLxConfig scConfig = ConfigSupplier.getConfig(p);

    lx = new LX(scConfig.getModel());
    scConfig.init(lx);

    // Config default palette to do interesting things
    lx.palette.hueMode.setValue(LXPalette.Mode.OSCILLATE);
    lx.palette.period.setValue(10000); // 10sec
    lx.palette.range.setValue(130); // to about green

    loadDefaultProject(p, scConfig);

    // Kick it!
    System.out.println("Starting engine!");
    lx.engine.start();


    // Safety: Turn everything off on shutdown
    Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

      public void run () {

        System.out.println("Shutting down: turning all off...");

        lx.engine.output.mode.setValue(LXOutput.Mode.OFF);
        lx.engine.output.send(null);

        // wait a few ms for all commands to flush
        long waitUntil = System.currentTimeMillis() + 100;
        while (System.currentTimeMillis() < waitUntil) {
          Thread.yield();
        }
      }
    }
    ));

    System.out.println("Setup complete, drawing blinks!");
  }

  private void loadDefaultProject(AppHeadless p, StarcatsLxConfig scConfig) {
    Optional<String> lxProjectToLoad = scConfig.getLxProjectToLoad();
    if (!lxProjectToLoad.isPresent()) {
      System.out.println("No defined presets, not loading anything");
    } else {
      File file = p.saveFile(lxProjectToLoad.get());
      if (!file.exists()) {
        System.out.println("Presets file '" + lxProjectToLoad.get() + "' not found! Not loading presets!");
      } else {
        lx.openProject(file);
        System.out.println("Successfully loaded presets: " + lxProjectToLoad.get());
      }
    }
  }

  public void draw() {
    // Wipe the frame...
    background(0x000);

    lx.engine.onDraw();

    if (!lx.engine.isThreaded()) {
      lx.engine.run();
    }
  }

}
