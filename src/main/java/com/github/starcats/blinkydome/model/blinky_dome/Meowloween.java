package com.github.starcats.blinkydome.model.blinky_dome;

import heronarts.lx.transform.LXVector;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Creates a {@link BlinkyModel} consisting of 4 triangles arranged in a simple "on the floor" geometry,
 * the geometry being interesting enough to show you how to use {@link BlinkyTriangle#positionIn3DSpace}
 *
 *
 */
public class Meowloween {
  static final public float TRIANGLE_SIDE_LENGTH = 18;
  static final private float DEG_30 = (float) Math.PI / 6.0f;
  static final private float DEG_60 = (float) Math.PI / 3.0f;
  static final private float DEG_120 = 2 * DEG_60;
  static final private float DEG_180 = (float) Math.PI;

  private static final LXVector X_UNIT_VECTOR = new LXVector(1, 0, 0);
  private static final LXVector Y_UNIT_VECTOR = new LXVector(0, 1, 0);
  private static final LXVector Z_UNIT_VECTOR = new LXVector(0, 0, 1);

  enum DomeGroup {
    WEST_WALL_CLUSTER(0),
    SOUTH_WALL_RIGHT_CLUSTER(1),
    SOUTH_WALL_LEFT_CLUSTER(2),
    TETRA_DJ(3),
    TETRA_MID(4),
    TETRA_FAR(5),
    ;

    private final int domeGroup;

    DomeGroup(int domeGroup) {
      this.domeGroup = domeGroup;
    }

    public int getDomeGroup() {
      return domeGroup;
    }
  }

  public static BlinkyModel makeModel() {

    LXVector negativeYUnitVector = new LXVector(0, -1, 0);
    LXVector positiveYUnitVector = new LXVector(0, 1, 0);

    LXVector right = new LXVector(1, 0, 0);

    LXVector southWallLeft = new LXVector(0, 0, -1);
    LXVector southWallRight = new LXVector(0, 0, 1);

    LXVector windowsRight = new LXVector(1, 0, 0);
    LXVector windowsLeft = new LXVector(-1, 0, 0);

    LXVector loftWindowRight = new LXVector(-1, 0, -1);
    LXVector loftWallRight = new LXVector(1, 0, -1);
    LXVector loftMirrorRight = new LXVector(-1, 0, -1);

    LXVector yAndNegativeZ = new LXVector(0, 1, -1);
    LXVector negativeXAndy = new LXVector(-1, 1, 0);
    LXVector yAndz = new LXVector(0, 1, 1);
    LXVector negYAndZ = new LXVector(0, -1, 1);
    LXVector negYAndNegZ = new LXVector(0, -1, -1);

    int crackToWindows = 194;
    int bottomTriBottomY = 20;
    int southWallY = 87;
    int ceilingHeight = 105 + 20;

    ArrayList<BlinkyTriangle> triangles = new ArrayList<>();

    /**
     * South Wall
     * Axis: Z
     *
     * This is the big wall with the blacklight crap on it.
     */
    int southWallX = -117;

    // Hour glass shape
    // TOP hour glass
    int southWallChunkZ = -129 + 36;
    triangles.add(BlinkyTriangle.positionIn3DSpace(
        new LXVector(southWallX, bottomTriBottomY + 16 + 31, southWallChunkZ),
        TRIANGLE_SIDE_LENGTH, DEG_120,
        negativeYUnitVector, southWallLeft,
        BlinkyTriangle.V.V1, BlinkyTriangle.V.V2,
        1, 3, 3 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE,
        DomeGroup.SOUTH_WALL_LEFT_CLUSTER.getDomeGroup(), 0
    ));
    // bottom hour glass
    triangles.add(BlinkyTriangle.positionIn3DSpace(
        new LXVector(southWallX, bottomTriBottomY + 16 + 31, southWallChunkZ),
        TRIANGLE_SIDE_LENGTH, DEG_180 + DEG_120,
        negativeYUnitVector, southWallLeft,
        BlinkyTriangle.V.V1, BlinkyTriangle.V.V2,
        1, 2, 1 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE,
        DomeGroup.SOUTH_WALL_LEFT_CLUSTER.getDomeGroup(), 1
    ));

    // Touching bottom hour glass
    triangles.add(BlinkyTriangle.positionIn3DSpace(
        new LXVector(southWallX, bottomTriBottomY + 16, southWallChunkZ + 16),
        TRIANGLE_SIDE_LENGTH, DEG_120,
        negativeYUnitVector, southWallLeft,
        BlinkyTriangle.V.V1, BlinkyTriangle.V.V2,
        1, 3, 2 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE,
        DomeGroup.SOUTH_WALL_LEFT_CLUSTER.getDomeGroup(), 2
    ));
    // Butted up against the one touching the bottom hour glass
    triangles.add(BlinkyTriangle.positionIn3DSpace(
        new LXVector(southWallX, bottomTriBottomY + 16, southWallChunkZ + 16),
        TRIANGLE_SIDE_LENGTH, DEG_180,
        negativeYUnitVector, southWallLeft,
        BlinkyTriangle.V.V1, BlinkyTriangle.V.V2,
        1, 3, 1 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE,
        DomeGroup.SOUTH_WALL_LEFT_CLUSTER.getDomeGroup(), 3
    ));

    // Top of the three big triangles
    triangles.add(BlinkyTriangle.positionIn3DSpace(
        new LXVector(southWallX, bottomTriBottomY + 31, (int)(southWallChunkZ + (2.5 * TRIANGLE_SIDE_LENGTH))),
        TRIANGLE_SIDE_LENGTH, DEG_180 + DEG_120,
        negativeYUnitVector, southWallLeft,
        BlinkyTriangle.V.V1, BlinkyTriangle.V.V2,
        1, 3, 0 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE,
        DomeGroup.SOUTH_WALL_LEFT_CLUSTER.getDomeGroup(), 4
    ));

    // Left bottom big triangle
    triangles.add(BlinkyTriangle.positionIn3DSpace(
        new LXVector(southWallX, bottomTriBottomY, southWallChunkZ + 26),
        TRIANGLE_SIDE_LENGTH, DEG_180,
        negativeYUnitVector, southWallLeft,
        BlinkyTriangle.V.V1, BlinkyTriangle.V.V2,
        1, 5, 3 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE,
        DomeGroup.SOUTH_WALL_LEFT_CLUSTER.getDomeGroup(), 5
    ));

    // Right bottom big triangle
    triangles.add(BlinkyTriangle.positionIn3DSpace(
            new LXVector(southWallX, bottomTriBottomY, southWallChunkZ + 64),
            TRIANGLE_SIDE_LENGTH, DEG_180+DEG_60+DEG_180,
            negativeYUnitVector, southWallLeft,
            BlinkyTriangle.V.V1, BlinkyTriangle.V.V2,
            1, 2, 0 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE,
            DomeGroup.SOUTH_WALL_LEFT_CLUSTER.getDomeGroup(), 6
    ));


    // same wall as chris' room door
    triangles.add(BlinkyTriangle.positionIn3DSpace(
            new LXVector(southWallX+6, ceilingHeight, southWallChunkZ + 136 - 40),
            TRIANGLE_SIDE_LENGTH, DEG_180+DEG_120,
            negativeYUnitVector, loftMirrorRight,
            BlinkyTriangle.V.V1, BlinkyTriangle.V.V2,
            0, 2, 3 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE,
            DomeGroup.SOUTH_WALL_LEFT_CLUSTER.getDomeGroup(), 7
    ));

    // ceiling above chris door
    int ceilingAboveChrisDoorZ = southWallChunkZ + 174 - 36;
    int tetraMidZ = ceilingAboveChrisDoorZ - 30;
    triangles.add(BlinkyTriangle.positionIn3DSpace(
            new LXVector(southWallX+40, ceilingHeight, ceilingAboveChrisDoorZ),
            TRIANGLE_SIDE_LENGTH, DEG_120+DEG_30+DEG_120,
            loftMirrorRight, loftWallRight,
            BlinkyTriangle.V.V1, BlinkyTriangle.V.V2,
            0, 2, 2 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE,
            DomeGroup.SOUTH_WALL_LEFT_CLUSTER.getDomeGroup(), 8
    ));

    triangles.add(BlinkyTriangle.positionIn3DSpace(
            new LXVector(southWallX+40, ceilingHeight, ceilingAboveChrisDoorZ - 60),
            TRIANGLE_SIDE_LENGTH, DEG_120+DEG_30+DEG_120,
            loftMirrorRight, loftWallRight,
            BlinkyTriangle.V.V1, BlinkyTriangle.V.V2,
            0, 2, 1 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE,
            DomeGroup.SOUTH_WALL_LEFT_CLUSTER.getDomeGroup(), 8
    ));

    // tetra 1 (top)
    triangles.add(BlinkyTriangle.positionIn3DSpace(
            new LXVector(southWallX+80, ceilingHeight - 12 , tetraMidZ),
            TRIANGLE_SIDE_LENGTH, DEG_120+DEG_30+DEG_120,
            X_UNIT_VECTOR, yAndNegativeZ,
            BlinkyTriangle.V.V1, BlinkyTriangle.V.V2,
            0, 7, 1 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE,
            DomeGroup.TETRA_MID.getDomeGroup(), 8
    ));
    // tetra 1 (top)
    triangles.add(BlinkyTriangle.positionIn3DSpace(
            new LXVector(southWallX+80, ceilingHeight - 12 , tetraMidZ),
            TRIANGLE_SIDE_LENGTH, DEG_120+DEG_30+DEG_120,
            X_UNIT_VECTOR, yAndz,
            BlinkyTriangle.V.V1, BlinkyTriangle.V.V2,
            0, 7, 2 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE,
            DomeGroup.TETRA_MID.getDomeGroup(), 8
    ));
    // tetra 1 (bottom)
    triangles.add(BlinkyTriangle.positionIn3DSpace(
        new LXVector(southWallX+80, ceilingHeight - 12 , tetraMidZ),
        TRIANGLE_SIDE_LENGTH, - DEG_60,
        new LXVector(0, 0, 1), negYAndNegZ,
        BlinkyTriangle.V.V1, BlinkyTriangle.V.V2,
        0, 7, 3 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE,
        DomeGroup.TETRA_MID.getDomeGroup(), 8
    ));
    // tetra 1 (bottom)
    triangles.add(BlinkyTriangle.positionIn3DSpace(
        new LXVector(southWallX+80, ceilingHeight - 12, tetraMidZ),
        TRIANGLE_SIDE_LENGTH, - DEG_60,
        new LXVector(0, 0, 1), negYAndNegZ,
        BlinkyTriangle.V.V1, BlinkyTriangle.V.V2,
        0, 7, 0 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE,
        DomeGroup.TETRA_MID.getDomeGroup(), 8
    ));

    // tetra 2 (top)
    triangles.add(BlinkyTriangle.positionIn3DSpace(
        new LXVector(southWallX+80, ceilingHeight - 12 , tetraMidZ + 36),
        TRIANGLE_SIDE_LENGTH, DEG_120+DEG_30+DEG_120,
        X_UNIT_VECTOR, yAndNegativeZ,
        BlinkyTriangle.V.V1, BlinkyTriangle.V.V2,
        0, 1, 1 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE,
        DomeGroup.TETRA_DJ.getDomeGroup(), 8
    ));
    // tetra 2 (top)
    triangles.add(BlinkyTriangle.positionIn3DSpace(
        new LXVector(southWallX+80, ceilingHeight - 12 , tetraMidZ + 36),
        TRIANGLE_SIDE_LENGTH, DEG_120+DEG_30+DEG_120,
        X_UNIT_VECTOR, yAndz,
        BlinkyTriangle.V.V1, BlinkyTriangle.V.V2,
        0, 1, 2 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE,
        DomeGroup.TETRA_DJ.getDomeGroup(), 8
    ));
    // tetra 2 (bottom)
    triangles.add(BlinkyTriangle.positionIn3DSpace(
        new LXVector(southWallX+80, ceilingHeight - 12 , tetraMidZ + 36),
        TRIANGLE_SIDE_LENGTH, - DEG_60,
        new LXVector(0, 0, 1), negYAndNegZ,
        BlinkyTriangle.V.V1, BlinkyTriangle.V.V2,
        0, 1, 3 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE,
        DomeGroup.TETRA_DJ.getDomeGroup(), 8
    ));
    // tetra 2 (bottom)
    triangles.add(BlinkyTriangle.positionIn3DSpace(
        new LXVector(southWallX+80, ceilingHeight - 12, tetraMidZ + 36),
        TRIANGLE_SIDE_LENGTH, - DEG_60,
        new LXVector(0, 0, 1), negYAndNegZ,
        BlinkyTriangle.V.V1, BlinkyTriangle.V.V2,
        0, 1, 0 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE,
        DomeGroup.TETRA_DJ.getDomeGroup(), 8
    ));

    // tetra 3 (top)
    triangles.add(BlinkyTriangle.positionIn3DSpace(
        new LXVector(southWallX+80, ceilingHeight - 12 , tetraMidZ - 36),
        TRIANGLE_SIDE_LENGTH, DEG_120+DEG_30+DEG_120,
        X_UNIT_VECTOR, yAndNegativeZ,
        BlinkyTriangle.V.V1, BlinkyTriangle.V.V2,
        1, 1, 1 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE,
        DomeGroup.TETRA_FAR.getDomeGroup(), 8
    ));
    // tetra 3 (top)
    triangles.add(BlinkyTriangle.positionIn3DSpace(
        new LXVector(southWallX+80, ceilingHeight - 12 , tetraMidZ - 36),
        TRIANGLE_SIDE_LENGTH, DEG_120+DEG_30+DEG_120,
        X_UNIT_VECTOR, yAndz,
        BlinkyTriangle.V.V1, BlinkyTriangle.V.V2,
        1, 1, 2 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE,
        DomeGroup.TETRA_FAR.getDomeGroup(), 8
    ));
    // tetra 3 (bottom)
    triangles.add(BlinkyTriangle.positionIn3DSpace(
        new LXVector(southWallX+80, ceilingHeight - 12 , tetraMidZ - 36),
        TRIANGLE_SIDE_LENGTH, - DEG_60,
        new LXVector(0, 0, 1), negYAndNegZ,
        BlinkyTriangle.V.V1, BlinkyTriangle.V.V2,
        1, 1, 3 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE,
        DomeGroup.TETRA_FAR.getDomeGroup(), 8
    ));
    // tetra 3 (bottom)
    triangles.add(BlinkyTriangle.positionIn3DSpace(
        new LXVector(southWallX+80, ceilingHeight - 12, tetraMidZ - 36),
        TRIANGLE_SIDE_LENGTH, - DEG_60,
        new LXVector(0, 0, 1), negYAndNegZ,
        BlinkyTriangle.V.V1, BlinkyTriangle.V.V2,
        1, 1, 0 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE,
        DomeGroup.TETRA_FAR.getDomeGroup(), 8
    ));



    /*
    // tetra 3
    triangles.add(BlinkyTriangle.positionIn3DSpace(
            new LXVector(southWallX+80, southWallY + 5 , -129 + 174),
            TRIANGLE_SIDE_LENGTH, DEG_120+DEG_30+DEG_120,
            yAndz, yAndNegativeZ,
            BlinkyTriangle.V.V1, BlinkyTriangle.V.V2,
            0, 2, 2 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE,
            DomeGroup.SOUTH_WALL_LEFT_CLUSTER.getDomeGroup(), 8
    ));

    // tetra 4
    triangles.add(BlinkyTriangle.positionIn3DSpace(
            new LXVector(southWallX+80, southWallY + 5 , -129 + 174),
            TRIANGLE_SIDE_LENGTH, DEG_120+DEG_30+DEG_120,
            yAndz, negativeXAndy,
            BlinkyTriangle.V.V1, BlinkyTriangle.V.V2,
            0, 2, 2 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE,
            DomeGroup.SOUTH_WALL_LEFT_CLUSTER.getDomeGroup(), 8
    ));
    */

    triangles.addAll(getHazardSignShape(new LXVector(southWallX, ceilingHeight - 16, -19),
        new LXVector(-1, 0, 0), DomeGroup.SOUTH_WALL_RIGHT_CLUSTER, 1, 5));

    /*triangles.add(BlinkyTriangle.positionIn3DSpace(
        new LXVector(southWallX, southWallY, -19),
        TRIANGLE_SIDE_LENGTH, 0,
        negativeYUnitVector, southWallRight,
        BlinkyTriangle.V.V1, BlinkyTriangle.V.V2,
        0, 1, 0,
        DomeGroup.SOUTH_WALL_RIGHT_CLUSTER.getDomeGroup(), 0
    ));*/
    /*
    triangles.add(BlinkyTriangle.positionIn3DSpace(
        new LXVector(southWallX, southWallY, -19),
        TRIANGLE_SIDE_LENGTH, -DEG_60,
        Y_UNIT_VECTOR, southWallRight,
        BlinkyTriangle.V.V1, BlinkyTriangle.V.V2,
        0, 1, 0,
        DomeGroup.SOUTH_WALL_RIGHT_CLUSTER.getDomeGroup(), 0
    ));
    triangles.add(BlinkyTriangle.positionIn3DSpace(
        new LXVector(southWallX, southWallY, -27),
        TRIANGLE_SIDE_LENGTH, 0,
        Y_UNIT_VECTOR, southWallLeft,
        BlinkyTriangle.V.V1, BlinkyTriangle.V.V3,
        0, 1, BlinkyTriangle.NUM_LEDS_PER_TRIANGLE,
        DomeGroup.SOUTH_WALL_RIGHT_CLUSTER.getDomeGroup(), 1
    ));
    triangles.add(BlinkyTriangle.positionIn3DSpace(
        new LXVector(southWallX, southWallY, -53),
        TRIANGLE_SIDE_LENGTH, -DEG_60,
        Y_UNIT_VECTOR, southWallRight,
        BlinkyTriangle.V.V1, BlinkyTriangle.V.V2,
        0, 1, 2 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE,
        DomeGroup.SOUTH_WALL_RIGHT_CLUSTER.getDomeGroup(), 2
    ));
    triangles.add(BlinkyTriangle.positionIn3DSpace(
        new LXVector(southWallX, southWallY, -58),
        TRIANGLE_SIDE_LENGTH, 0,
        Y_UNIT_VECTOR, southWallLeft,
        BlinkyTriangle.V.V1, BlinkyTriangle.V.V3,
        0, 1, 3 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE,
        DomeGroup.SOUTH_WALL_RIGHT_CLUSTER.getDomeGroup(), 3
    ));
    */

    /*
    // Facing east
    triangles.add(BlinkyTriangle.positionIn3DSpace(
        new LXVector(southWallX, southWallY, -129),
        TRIANGLE_SIDE_LENGTH, 0,
        negativeYUnitVector, southWallLeft,
        BlinkyTriangle.V.V1, BlinkyTriangle.V.V2,
        0, 1, 0 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE,
        DomeGroup.TETRA_DJ.getDomeGroup(), 0
    ));

    // Facing Chris Room
    triangles.add(BlinkyTriangle.positionIn3DSpace(
        new LXVector(southWallX, southWallY, -129),
        TRIANGLE_SIDE_LENGTH, 0,
        negativeYUnitVector, southWallLeft,
        BlinkyTriangle.V.V1, BlinkyTriangle.V.V2,
        0, 1, 1 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE,
        DomeGroup.TETRA_DJ.getDomeGroup(), 1
    ));

    // Facing north
    triangles.add(BlinkyTriangle.positionIn3DSpace(
        new LXVector(southWallX, southWallY, -129),
        TRIANGLE_SIDE_LENGTH, 0,
        negativeYUnitVector, southWallLeft,
        BlinkyTriangle.V.V1, BlinkyTriangle.V.V2,
        0, 1, 2 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE,
        DomeGroup.TETRA_DJ.getDomeGroup(), 2
    ));

    // Bottom
    triangles.add(BlinkyTriangle.positionIn3DSpace(
        new LXVector(southWallX, southWallY - 15, -129),
        TRIANGLE_SIDE_LENGTH, 0,
        X_UNIT_VECTOR, Z_UNIT_VECTOR,
        BlinkyTriangle.V.V1, BlinkyTriangle.V.V2,
        0, 1, 3 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE,
        DomeGroup.TETRA_DJ.getDomeGroup(), 3
    ));
    */

    /**
     * Random one on the ceiling near crawl space
     *
     * TODO: VERY WRONG POSITION
     */
    triangles.add(BlinkyTriangle.positionIn3DSpace(
        new LXVector(southWallX + (12 * 10), ceilingHeight, -129),
        TRIANGLE_SIDE_LENGTH, 0,
        X_UNIT_VECTOR, Z_UNIT_VECTOR,
        BlinkyTriangle.V.V1, BlinkyTriangle.V.V2,
        0, 2, 0 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE,
        DomeGroup.TETRA_DJ.getDomeGroup(), 3
    ));

    /**
     * Strand under crawl space
     */
    /*
    triangles.add(BlinkyTriangle.positionIn3DSpace(
        new LXVector(23, 6 * 12, -18),
        TRIANGLE_SIDE_LENGTH, DEG_30,
        X_UNIT_VECTOR, Z_UNIT_VECTOR,
        BlinkyTriangle.V.V1, BlinkyTriangle.V.V2,
        0, 3, 0 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE,
        DomeGroup.TETRA_DJ.getDomeGroup(), 3
    ));

    triangles.add(BlinkyTriangle.positionIn3DSpace(
        new LXVector(33, 95, -60),
        TRIANGLE_SIDE_LENGTH, -DEG_60,
        Y_UNIT_VECTOR, loftMirrorRight,
        BlinkyTriangle.V.V1, BlinkyTriangle.V.V2,
        0, 3, 1 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE, 0, 0
    ));

    triangles.add(BlinkyTriangle.positionIn3DSpace(
        new LXVector(5, 89, -80),
        TRIANGLE_SIDE_LENGTH, -DEG_60,
        Y_UNIT_VECTOR, loftMirrorRight,
        BlinkyTriangle.V.V1, BlinkyTriangle.V.V2,
        0, 3, 2 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE, 0, 0
    ));
    */

    /**
     * Random one on the ceiling near crawl space
     */
    triangles.add(BlinkyTriangle.positionIn3DSpace(
        new LXVector(southWallX + (12 * 10) /* call it 10ft */, ceilingHeight, tetraMidZ),
        TRIANGLE_SIDE_LENGTH, 0,
        X_UNIT_VECTOR, Z_UNIT_VECTOR,
        BlinkyTriangle.V.V1, BlinkyTriangle.V.V2,
        0, 2, 0 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE,
        DomeGroup.TETRA_DJ.getDomeGroup(), 3
    ));

    /**
     * Strand under crawl space
     */
    /*
    TODO: UNDO
    triangles.add(BlinkyTriangle.positionIn3DSpace(
        new LXVector(23, 78, -18),
        TRIANGLE_SIDE_LENGTH, DEG_30,
        X_UNIT_VECTOR, Z_UNIT_VECTOR,
        BlinkyTriangle.V.V1, BlinkyTriangle.V.V2,
        0, 3, 0 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE,
        DomeGroup.TETRA_DJ.getDomeGroup(), 3
    ));

    triangles.add(BlinkyTriangle.positionIn3DSpace(
        new LXVector(33, 96, -60),
        TRIANGLE_SIDE_LENGTH, -DEG_60,
        Y_UNIT_VECTOR, loftMirrorRight,
        BlinkyTriangle.V.V1, BlinkyTriangle.V.V2,
        0, 3, 1 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE, 0, 0
    ));

    triangles.add(BlinkyTriangle.positionIn3DSpace(
        new LXVector(5, 90, -80),
        TRIANGLE_SIDE_LENGTH, -DEG_60,
        Y_UNIT_VECTOR, loftMirrorRight,
        BlinkyTriangle.V.V1, BlinkyTriangle.V.V2,
        0, 3, 2 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE, 0, 0
    ));
    */

    /*
    // Facing east
    triangles.add(BlinkyTriangle.positionIn3DSpace(
        new LXVector(southWallX, southWallY, -129),
        TRIANGLE_SIDE_LENGTH, 0,
        negativeYUnitVector, southWallLeft,
        BlinkyTriangle.V.V1, BlinkyTriangle.V.V2,
        0, 1, 0 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE,
        DomeGroup.TETRA_DJ.getDomeGroup(), 0
    ));

    // Facing Chris Room
    triangles.add(BlinkyTriangle.positionIn3DSpace(
        new LXVector(southWallX, southWallY, -129),
        TRIANGLE_SIDE_LENGTH, 0,
        negativeYUnitVector, southWallLeft,
        BlinkyTriangle.V.V1, BlinkyTriangle.V.V2,
        0, 1, 1 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE,
        DomeGroup.TETRA_DJ.getDomeGroup(), 1
    ));

    // Facing north
    triangles.add(BlinkyTriangle.positionIn3DSpace(
        new LXVector(southWallX, southWallY, -129),
        TRIANGLE_SIDE_LENGTH, 0,
        negativeYUnitVector, southWallLeft,
        BlinkyTriangle.V.V1, BlinkyTriangle.V.V2,
        0, 1, 2 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE,
        DomeGroup.TETRA_DJ.getDomeGroup(), 2
    ));

    // Bottom
    triangles.add(BlinkyTriangle.positionIn3DSpace(
        new LXVector(southWallX, southWallY - 15, -129),
        TRIANGLE_SIDE_LENGTH, 0,
        X_UNIT_VECTOR, Z_UNIT_VECTOR,
        BlinkyTriangle.V.V1, BlinkyTriangle.V.V2,
        0, 1, 3 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE,
        DomeGroup.TETRA_DJ.getDomeGroup(), 3
    ));
    */

    /**
     * Random one on the ceiling near crawl space
     */
    triangles.add(BlinkyTriangle.positionIn3DSpace(
        new LXVector(southWallX + (12 * 10) /* call it 10ft */, southWallY - 15, -129),
        TRIANGLE_SIDE_LENGTH, 0,
        X_UNIT_VECTOR, Z_UNIT_VECTOR,
        BlinkyTriangle.V.V1, BlinkyTriangle.V.V2,
        0, 2, 0 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE,
        DomeGroup.TETRA_DJ.getDomeGroup(), 3
    ));

    /**
     * Strand under crawl space
     */
    triangles.add(BlinkyTriangle.positionIn3DSpace(
        new LXVector(23, 6 * 12, -18),
        TRIANGLE_SIDE_LENGTH, DEG_30,
        X_UNIT_VECTOR, Z_UNIT_VECTOR,
        BlinkyTriangle.V.V1, BlinkyTriangle.V.V2,
        0, 3, 0 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE,
        DomeGroup.TETRA_DJ.getDomeGroup(), 3
    ));

    // Left of crawlspace mirror
    triangles.add(BlinkyTriangle.positionIn3DSpace(
        new LXVector(33, 95, -60),
        TRIANGLE_SIDE_LENGTH, -DEG_60,
        Y_UNIT_VECTOR, loftMirrorRight,
        BlinkyTriangle.V.V1, BlinkyTriangle.V.V2,
        0, 3, 1 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE, 0, 0
    ));

    // Right of crawlspace mirror
    triangles.add(BlinkyTriangle.positionIn3DSpace(
        new LXVector(5, 89, -80),
        TRIANGLE_SIDE_LENGTH, -DEG_60,
        Y_UNIT_VECTOR, loftMirrorRight,
        BlinkyTriangle.V.V1, BlinkyTriangle.V.V2,
        0, 3, 2 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE, 0, 0
    ));

    // Black light lounge
    triangles.add(BlinkyTriangle.positionIn3DSpace(
        new LXVector(5, 100, -100),
        TRIANGLE_SIDE_LENGTH, -DEG_60,
        Y_UNIT_VECTOR, new LXVector(1, 0, -1),
        BlinkyTriangle.V.V1, BlinkyTriangle.V.V2,
        0, 3, 3 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE, 0, 0
    ));


    // ------------------
    // Old Loft Triangles

    /*
    triangles.add(BlinkyTriangle.positionIn3DSpace(
                    new LXVector(33, 81, 8),
                TRIANGLE_SIDE_LENGTH, 0,
                    Y_UNIT_VECTOR, loftWindowRight,
                    BlinkyTriangle.V.V1, BlinkyTriangle.V.V2,
                    1, 2, 0 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE, 0, 0
            ));

    triangles.add(BlinkyTriangle.positionIn3DSpace(
                    new LXVector(22, 95, -19),
                TRIANGLE_SIDE_LENGTH, -DEG_60,
                    Y_UNIT_VECTOR, loftWallRight,
                    BlinkyTriangle.V.V1, BlinkyTriangle.V.V3,
                    1, 2, 1 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE, 0, 0
            ));

    triangles.add(BlinkyTriangle.positionIn3DSpace(
                    new LXVector(22, 95, -26),
                TRIANGLE_SIDE_LENGTH, 0,
                    Y_UNIT_VECTOR, loftWallRight,
                    BlinkyTriangle.V.V1, BlinkyTriangle.V.V3,
                    1, 2, 2 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE, 0, 0
            ));
            */



    // ------------------
            // West wall / windows

            /*
            // windows 1
            BlinkyTriangle.positionIn3DSpace(
                    new LXVector(49, 90, 176),
                TRIANGLE_SIDE_LENGTH, - (float) Math.PI + DEG_60,
                    Y_UNIT_VECTOR, southWallLeft,
                    BlinkyTriangle.V.V3, BlinkyTriangle.V.V2,
                    1, 1, 0 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE, 0, 0
            ),
            */

    /**
     * Name: West Wall.
     * Axis: X
     *
     * This is the back nook area in Dore, by the fire escape door.
     */
    int westWallSmallestX = -15;
    int westWallY = 90;
    int westWallZ = 206;

    triangles.add(BlinkyTriangle.positionIn3DSpace(
        new LXVector(westWallSmallestX + (TRIANGLE_SIDE_LENGTH * 2), westWallY, westWallZ),
        TRIANGLE_SIDE_LENGTH, -DEG_60,
        Y_UNIT_VECTOR, windowsLeft,
        BlinkyTriangle.V.V1, BlinkyTriangle.V.V2,
        0, 6, 0 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE,
        DomeGroup.WEST_WALL_CLUSTER.getDomeGroup(), 0
    ));
    triangles.add(BlinkyTriangle.positionIn3DSpace(
        new LXVector(westWallSmallestX + (TRIANGLE_SIDE_LENGTH * 2) + 6, westWallY, westWallZ),
        TRIANGLE_SIDE_LENGTH, 0,
        Y_UNIT_VECTOR, windowsRight,
        BlinkyTriangle.V.V1, BlinkyTriangle.V.V3,
        0, 6, 1 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE,
        DomeGroup.WEST_WALL_CLUSTER.getDomeGroup(), 1
    ));
    triangles.add(BlinkyTriangle.positionIn3DSpace(
        new LXVector(westWallSmallestX + 6, westWallY, westWallZ),
        TRIANGLE_SIDE_LENGTH, 0,
        Y_UNIT_VECTOR, windowsRight,
        BlinkyTriangle.V.V1, BlinkyTriangle.V.V3,
        0, 6, 2 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE,
        DomeGroup.WEST_WALL_CLUSTER.getDomeGroup(), 2
    ));
    triangles.add(BlinkyTriangle.positionIn3DSpace(
        new LXVector(westWallSmallestX, westWallY, westWallZ),
        TRIANGLE_SIDE_LENGTH, -DEG_60,
        Y_UNIT_VECTOR, windowsLeft,
        BlinkyTriangle.V.V3, BlinkyTriangle.V.V1,
        0, 6, 3 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE,
        DomeGroup.WEST_WALL_CLUSTER.getDomeGroup(), 3
    ));




    return BlinkyModel.makeModel(triangles);
  }

  static List<BlinkyTriangle> getHazardSignShape(LXVector centerPoint, LXVector axisToRotateAround, DomeGroup domeGroup, int ppGroup, int ppPort) {
    LXVector rightVector = axisToRotateAround.cross(Y_UNIT_VECTOR);

    return Arrays.asList(
        BlinkyTriangle.positionIn3DSpace(
            centerPoint,
            TRIANGLE_SIDE_LENGTH, DEG_60 * 3,
            Y_UNIT_VECTOR, rightVector,
            BlinkyTriangle.V.V1, BlinkyTriangle.V.V2,
            ppGroup, ppPort, 0 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE,
            domeGroup.getDomeGroup(), 0
        ),
        BlinkyTriangle.positionIn3DSpace(
            centerPoint,
            TRIANGLE_SIDE_LENGTH, -DEG_60,
            Y_UNIT_VECTOR, rightVector,
            BlinkyTriangle.V.V1, BlinkyTriangle.V.V2,
            ppGroup, ppPort, 1 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE,
            domeGroup.getDomeGroup(), 1
        ),
        BlinkyTriangle.positionIn3DSpace(
            centerPoint,
            TRIANGLE_SIDE_LENGTH, DEG_60,
            Y_UNIT_VECTOR, rightVector,
            BlinkyTriangle.V.V1, BlinkyTriangle.V.V2,
            ppGroup, ppPort, 2 * BlinkyTriangle.NUM_LEDS_PER_TRIANGLE,
            domeGroup.getDomeGroup(), 2
        )
        );
  }
}
