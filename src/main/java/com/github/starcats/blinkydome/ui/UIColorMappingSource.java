package com.github.starcats.blinkydome.ui;

import com.github.starcats.blinkydome.color.ImageColorSampler;
import com.github.starcats.blinkydome.color.ImageColorSamplerGroup;
import heronarts.lx.parameter.DiscreteParameter;
import heronarts.p3lx.ui.UI;
import heronarts.p3lx.ui.UI2dContainer;
import heronarts.p3lx.ui.component.UISwitch;
import heronarts.p3lx.ui.component.UIToggleSet;
import heronarts.p3lx.ui.studio.UICollapsibleSection;
import processing.core.PGraphics;
import processing.event.MouseEvent;

/**
 * UI to select a {@link com.github.starcats.blinkydome.color.ColorMappingSource} from a
 * {@link com.github.starcats.blinkydome.color.ColorMappingSourceClan}
 *
 * Currently chooses between gradients and patterns.
 */
public class UIColorMappingSource extends UICollapsibleSection {

  private static final float TOGGLES_W = 20;
  private static final float GROUP_SELECT_H = 15;
  private static final float ROW_SPACING = 5;
  private static final float GRADIENT_SUPPLIER_UIS_ROW_Y = UISwitch.WIDTH + ROW_SPACING + GROUP_SELECT_H;

  private final DiscreteParameter groupSelector;
  private final ImageColorSamplerUI[] imageColorSamplerUIs;
  private ImageColorSamplerUI currentSamplerUI;

  public UIColorMappingSource(UI lxUI, ImageColorSamplerGroup imageSamplerGroup, float x, float y, float w) {
    super(
        lxUI, x, y, w,
        30 + GRADIENT_SUPPLIER_UIS_ROW_Y
    );
    setTitle("COLOR MAPPING SRC");

    groupSelector = imageSamplerGroup.getFamilySelect();


    // Create UI elements:

    // First row: shuffle buttons
    new UISwitch(0, 0)
        .setParameter(imageSamplerGroup.getRandomSourceInFamilyTrigger())
        .addToContainer(this);

    new UISwitch(UISwitch.WIDTH, 0)
        .setParameter(imageSamplerGroup.getRandomSourceTrigger())
        .addToContainer(this);


    // Second row: group select buttons
    new UIToggleSet(
        0, UISwitch.WIDTH + ROW_SPACING, getContentWidth(), GROUP_SELECT_H
    )
    .setParameter(groupSelector)
    .addToContainer(this);


    // Third row: add all the sampler UI's (but hide inactive ones)

    ImageColorSampler[] imageSamplers = imageSamplerGroup.getFamilies();
    this.imageColorSamplerUIs = new ImageColorSamplerUI[imageSamplers.length];
    for (int i=0; i<imageSamplers.length; i++) {
      imageColorSamplerUIs[i] =  new ImageColorSamplerUI(
          imageSamplers[i], 0, GRADIENT_SUPPLIER_UIS_ROW_Y, getContentWidth()
      );

      boolean isSelected = imageColorSamplerUIs[i].sourceGroup == groupSelector.getObject();

      imageColorSamplerUIs[i]
      .addToContainer(this)
      .setVisible(isSelected);

      if (isSelected) {
        currentSamplerUI = imageColorSamplerUIs[i];
      }
    }

    // Wire up resizing according to whichever gradient supplier is visible
    resize();
    groupSelector.addListener(parameter -> {
      // Show only the matching UI
      for (ImageColorSamplerUI ui : imageColorSamplerUIs) {
        boolean isSelected = ui.sourceGroup == ((DiscreteParameter) parameter).getObject();
        ui.setVisible(isSelected);

        if (isSelected) {
          currentSamplerUI = ui;
        }
      }

      // and resize to whichever one became visible
      resize();
    });

  }

  /** Helper component that shows the patternMap and toggle switches for a given {@link ImageColorSampler} instance */
  private static class ImageColorSamplerUI extends UI2dContainer {
    final ImageColorSampler sourceGroup;

    private ImageColorSamplerUI(ImageColorSampler samplingSourceGroup, float x, float y, float w) {
      super(x, y, w, samplingSourceGroup.patternMap.height + 2);

      sourceGroup = samplingSourceGroup;

      new UIVerticalToggleSet(0, 0, TOGGLES_W, samplingSourceGroup.patternMap.height + 2)
      .setParameter(samplingSourceGroup.getSourceSelect())
      .addToContainer(this);

      new UIGradientImage(samplingSourceGroup, TOGGLES_W, 1, getContentWidth() - TOGGLES_W)
      .addToContainer(this);
    }
  }

  private static class UIGradientImage extends UI2dContainer {

    private ImageColorSampler gradientSupplier;

    protected UIGradientImage(ImageColorSampler gradientSupplier, float x, float y, float width) {
      super(x, y, width, gradientSupplier.patternMap.height);
      this.gradientSupplier = gradientSupplier;
    }

    @Override
    public void onDraw(UI ui, PGraphics pg) {
      pg.image(gradientSupplier.patternMap, 0, 0, width, gradientSupplier.patternMap.height);
    }

    @Override
    protected void onMousePressed(MouseEvent mouseEvent, float mx, float my) {
      gradientSupplier.getSourceSelect().setValue(gradientSupplier.getSamplerFromMouseY(my));
    }
  }

  private void resize() {
    this.setContentHeight(
        GRADIENT_SUPPLIER_UIS_ROW_Y +
        currentSamplerUI.getHeight()
    );
  }
}