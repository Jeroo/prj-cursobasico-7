package com.example.scuev.connect4_game;

import java.util.Arrays;

/**
 * Created by Salvador Cuevas on 5/18/2017.
 * Game connect4, the logic is to put chips one underneath another,
 * once a player manages to complete four chips of the same color in vertical,
 * horizontal or diagonal positions wins the game and increases his score.
 */

public class Connect4
{
    /*We have defined by default three colors starting from 0 which would be the blank position,
    blue which is the number 1 represented by a constant integer
    and red which is the number 2 also represented by a constant integer.
    */
    private static final int RED_COLOR  = 2,BLUE_COLOR = 1;
    private int              counterBlue,counterRed,turn,blueColorScore,redColorScore;
    boolean                  labelEndGame;
    private int[][]          matrixConnect4;

    public Connect4()
    {

        this.turn           = 1;
        this.labelEndGame   = false;
        this.matrixConnect4 =  new int[6][7];
        this.redColorScore  = 0;
        this.blueColorScore = 0;
    }

    void fillMatrixWithZero()
    {

        for (int[] row: matrixConnect4)
            Arrays.fill(row, 0);
    }

    boolean validateMovedOverCircle(int posX, int posY){

        /* Validating that the position in x is 5,
        * because if it is 5 we are in the last row.
        */
        if (posX == 5)
        {
            return true;

        }else
        {
            /*Checking the position below to be able to know if you can play in this selected position,
            otherwise we are in a position that does not have a tab below.
            */
            if (matrixConnect4[posX+1][posY] != 0)
                return true;

        }

        return false;
    }

    void fillMatrix(int posX, int posY,int color)
    {

        if (matrixConnect4[posX][posY] == 0)
        {
            /*First we fill the vector with the positions and the color that we received from the view
            which were selected by the user, then fill the matrix or vector.
            */
            matrixConnect4[posX][posY] = color;

            /*Then we run the vector in the 8 directions until we find four continuous tiles of the same color,
            if that happens the game stops and we announce the winner in an alert.
            */
            if (sortMatrixDown(posX,posY))
            {
                stopGame();

            }else if (sortMatrixRight(posX,posY))
            {
                stopGame();

            }else if (sortMatrixDiagonallyLeftDown(posX,posY))
            {
                stopGame();

            }else if (sortMatrixDiagonallyRightDown(posX,posY))
            {
                stopGame();

            }
        }
    }

    boolean sortMatrixRight(int posX, int posY)
    {
        if (posY == 6)
        {
            sortMatrixLeft(posX,posY);

        }else
        {

            if (matrixConnect4[posX][posY + 1] == matrixConnect4[posX][posY])
            {

                try
                {

                    for (int i = 0; i < 5; i++)
                    {

                        if (matrixConnect4[posX][posY + i] == matrixConnect4[posX][posY])
                        {

                            if (BLUE_COLOR == matrixConnect4[posX][posY])
                            {

                                counterBlue++;
                            }
                            if (RED_COLOR == matrixConnect4[posX][posY])
                            {

                                counterRed++;
                            }

                            if (counterBlue == 4 || counterRed ==4)
                            {
                                break;
                            }

                        } else
                        {

                            if (counterRed > 0 || counterBlue > 0)
                            {
                                sortMatrixLeft(posX,posY);
                            }

                            break;
                        }
                    }


                } catch (Exception ex)
                {

                    sortMatrixLeft(posX,posY);

                } finally {

                    if (counterRed == 4)
                    {

                        labelEndGame = true;
                    }

                    if (counterBlue == 4)
                    {

                        labelEndGame = true;
                    }

                }


            } else
            {
                sortMatrixLeft(posX,posY);

            }
        }

        counterBlue = 0;
        counterRed = 0;

        return labelEndGame;
    }

    boolean sortMatrixLeft(int posX,int posY)
    {

        if (posY == 0)
        {
            labelEndGame = false;

        }else
        {

            if (matrixConnect4[posX][posY - 1] == matrixConnect4[posX][posY])
            {

                try
                {

                    if (counterBlue > 1)
                    {
                        counterBlue--;
                    }

                    if (counterRed > 1)
                    {
                        counterRed--;
                    }

                    for (int i = 0; i < 5; i++)
                    {

                        if (matrixConnect4[posX][posY - i] == matrixConnect4[posX][posY])
                        {

                            if (BLUE_COLOR == matrixConnect4[posX][posY])
                            {
                                counterBlue++;
                            }
                            if (RED_COLOR == matrixConnect4[posX][posY])
                            {
                                counterRed++;
                            }

                            if (counterBlue == 4 || counterRed == 4)
                            {
                                break;
                            }

                        } else
                        {

                            if (counterBlue < 2)
                            {
                                counterBlue = 0;

                            } else if (counterRed < 2)
                            {
                                counterRed = 0;
                            }

                            break;
                        }

                    }

                } catch (Exception ex)
                {


                } finally
                {

                    if (counterRed == 4) {

                        labelEndGame = true;
                    }

                    if (counterBlue == 4) {

                        labelEndGame = true;
                    }


                }

            } else
            {
                labelEndGame = false;
            }
        }

        counterBlue = 0;
        counterRed = 0;
        return labelEndGame;
    }

    boolean sortMatrixDown(int posX, int posY)
    {

        if (posX == 5)
        {
            labelEndGame =  false;

        }else
        {

            if ((posX + 1) != 5 && matrixConnect4[posX + 1][posY] == matrixConnect4[posX][posY])
            {
                try
                {

                    for (int i = 0; i < 5; i++)
                    {

                        if (matrixConnect4[posX + i][posY] == matrixConnect4[posX][posY])
                        {

                            if (BLUE_COLOR == matrixConnect4[posX][posY])
                            {

                                counterBlue++;
                            }
                            if (RED_COLOR == matrixConnect4[posX][posY])
                            {

                                counterRed++;
                            }

                            if (counterBlue == 4 || counterRed ==4)
                            {
                                break;
                            }

                        } else
                        {

                            break;
                        }
                    }

                } catch (Exception ex)
                {
                    labelEndGame = false;

                } finally
                {
                    if (counterRed == 4)
                    {

                        labelEndGame = true;
                    }

                    if (counterBlue == 4)
                    {

                        labelEndGame = true;
                    }
                }

            } else
            {

                labelEndGame = false;

            }
        }

        counterBlue = 0;
        counterRed = 0;

        return  labelEndGame;
    }

    boolean sortMatrixDiagonallyLeftDown(int posX, int posY)
    {
        if (posX == 5)
        {
            sortMatrixDiagonallyLeftTop(posX,posY);

        }else
        {
            try
            {

                for (int i = 0; i < 5; i++)
                {

                    if ((posX+i) == 6)
                    {

                        sortMatrixDiagonallyLeftTop(posX, posY);
                        break;
                    }

                    if (matrixConnect4[posX + i][posY + i] == matrixConnect4[posX][posY])
                    {

                        if (BLUE_COLOR == matrixConnect4[posX][posY])
                        {

                            counterBlue++;
                        }
                        if (RED_COLOR == matrixConnect4[posX][posY])
                        {

                            counterRed++;
                        }

                        if (counterBlue == 4 || counterRed ==4)
                        {
                            break;
                        }

                    } else
                    {

                        if (counterRed > 0 || counterBlue > 0)
                        {
                            counterBlue = 0;
                            counterRed = 0;
                            sortMatrixDiagonallyLeftTop(posX,posY);
                        }
                        break;
                    }

                }

            } catch (Exception ex)
            {

                sortMatrixDiagonallyLeftTop(posX,posY);

            } finally
            {

                if (counterRed == 4)
                {

                    labelEndGame = true;
                }

                if (counterBlue == 4)
                {

                    labelEndGame = true;
                }
            }
        }

        counterBlue = 0;
        counterRed = 0;

        return labelEndGame;
    }

    boolean sortMatrixDiagonallyLeftTop(int posX,int posY)
    {

        if (posY == 0)
        {
            labelEndGame = false;

        }else {
            try {
                if (counterBlue > 1) {
                    counterBlue--;
                }

                if (counterRed > 1) {
                    counterRed--;
                }

                for (int i = 0; i < 5; i++) {

                    if (matrixConnect4[posX - i][posY - i] == matrixConnect4[posX][posY]) {

                        if (BLUE_COLOR == matrixConnect4[posX][posY]) {

                            counterBlue++;
                        }
                        if (RED_COLOR == matrixConnect4[posX][posY]) {

                            counterRed++;
                        }

                        if (counterBlue == 4 || counterRed == 4) {
                            break;
                        }

                    } else {

                        if (counterBlue < 2) {
                            counterBlue = 0;

                        } else if (counterRed < 2) {
                            counterRed = 0;
                        }

                        break;
                    }

                }

            } catch (Exception ex) {

                labelEndGame = false;

            } finally {

                if (counterRed == 4) {

                    labelEndGame = true;
                }

                if (counterBlue == 4) {

                    labelEndGame = true;
                }
            }
        }

        counterBlue = 0;
        counterRed = 0;

        return labelEndGame;
    }

    boolean sortMatrixDiagonallyRightDown(int posX, int posY)
    {

        if (posX == 5)
        {
            sortMatrixDiagonallyRightTop(posX,posY);

        }else
        {

            try
            {

                for (int i = 0; i < 5; i++)
                {

                    if ((posX+i) == 6)
                    {

                        sortMatrixDiagonallyRightTop(posX, posY);
                        break;
                    }

                    if (matrixConnect4[posX + i][posY - i] == matrixConnect4[posX][posY])
                    {

                        if (BLUE_COLOR == matrixConnect4[posX][posY])
                        {

                            counterBlue++;
                        }
                        if (RED_COLOR == matrixConnect4[posX][posY])
                        {
                            counterRed++;
                        }

                        if (counterBlue == 4 || counterRed ==4){
                            break;
                        }

                    } else
                    {
                        if (counterRed > 0 || counterBlue > 0)
                        {
                            counterBlue = 0;
                            counterRed = 0;
                            sortMatrixDiagonallyRightTop(posX,posY);
                        }
                        break;
                    }

                }

            } catch (Exception ex)
            {

                sortMatrixDiagonallyRightTop(posX,posY);

            } finally
            {

                if (counterRed == 4)
                {

                    labelEndGame = true;
                }

                if (counterBlue == 4)
                {

                    labelEndGame = true;
                }

            }
        }
        counterBlue = 0;
        counterRed = 0;
        return labelEndGame;
    }

    boolean sortMatrixDiagonallyRightTop(int posX,int posY)
    {

        if (posY == 6)
        {
            labelEndGame = false;

        }else
        {

            try
            {
                if (counterBlue > 1)
                {
                    counterBlue--;
                }

                if (counterRed > 1)
                {
                    counterRed--;
                }

                for (int i = 0; i < 5; i++)
                {

                    if (matrixConnect4[posX - i][posY + i] == matrixConnect4[posX][posY])
                    {

                        if (BLUE_COLOR == matrixConnect4[posX][posY])
                        {

                            counterBlue++;
                        }
                        if (RED_COLOR == matrixConnect4[posX][posY])
                        {

                            counterRed++;
                        }

                        if (counterBlue == 4 || counterRed == 4)
                        {
                            break;
                        }

                    } else
                    {

                        if (counterBlue < 2)
                        {
                            counterBlue = 0;

                        } else if (counterRed < 2)
                        {
                            counterRed = 0;
                        }

                        break;
                    }

                }

            } catch (Exception ex)
            {


            } finally
            {

                if (counterRed == 4)
                {

                    labelEndGame = true;
                }

                if (counterBlue == 4)
                {

                    labelEndGame = true;
                }
            }
        }
        counterBlue = 0;
        counterRed = 0;

        return labelEndGame;

    }

    boolean stopGame()
    {

        if (labelEndGame)
        {

            if (turn == 1)
            {
                setScoreGame(2);

            }else
            {
                setScoreGame(1);
            }
        }

        return labelEndGame;
    }

     void newGame()
    {
        this.counterBlue    = 0;
        this.counterRed     = 0;
        this.turn           = 1;
        this.labelEndGame   = false;
        this.matrixConnect4 =  new int[6][7];
        fillMatrixWithZero();
    }

     void setScoreGame(int colorWinner)
    {
        /*
        * if winner is 1 is blue win
        * then set label score with plus 1
        * else the winner is red and then set label score with plus 1
        * */
        if (colorWinner == BLUE_COLOR)
        {
            blueColorScore++;

        } else if (colorWinner == RED_COLOR)
        {
            redColorScore++;
        }
    }

    int getScoreGameRed()
    {
        return this.redColorScore;
    }

    int getScoreGameBlue()
    {
        return this.blueColorScore;
    }

    int getTurn()
    {
        return this.turn;
    }

    void setTurn(int turn)
    {
        this.turn = turn;
    }
}