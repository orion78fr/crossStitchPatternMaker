package fr.orion78.crossStitchPatternMaker;

public class Parameters {
  private int squareSize;
  private boolean greyScale;
  private int nbColors;

  public Parameters(){
    reset();
  }

  public void reset(){
    squareSize = 0;
    greyScale = false;
    nbColors = 0;
  }

  public int getSquareSize() {
    return squareSize;
  }

  public boolean isGreyScale() {
    return greyScale;
  }

  public int getNbColors() {
    return nbColors;
  }

  public void setSquareSize(int squareSize) {
    this.squareSize = squareSize;
  }

  public void switchGreyScale() {
    this.greyScale = !greyScale;
  }

  public void setNbColors(int nbColors) {
    this.nbColors = nbColors;
  }
}
