package existentialrps;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Tim Barber
 */
public class DeepThought {

    private int diff_level;
    private Random random = new Random();
    private ArrayList<String> pastMoves = new ArrayList<>();
    private boolean threeInARow = false;
    private String threeMove = "";

    public DeepThought() {
        diff_level = 0;
    }

    public DeepThought(int Diff_level) {
        this.diff_level = Diff_level;
    }

    public int getDiffLevel() {
        return this.diff_level;
    }

    public void setDiffLevel(int amt) {
        this.diff_level = amt;
    }

    public void addPastMove(String move) {
        this.pastMoves.add(move);
    }

    public String randomMove() {
        int temp = random.nextInt(3);
        if (temp == 0) {
            return "r";
        } else if (temp == 1) {
            return "s";
        } else {
            return "p";
        }
    }

    public String randomMove(String slant) {
        int temp = random.nextInt(4);
        String otherMove1 = "";
        String otherMove2 = "";
        if (slant.equals("r")) {
            otherMove1 = "p";
            otherMove2 = "s";
        } else if (slant.equals("p")) {
            otherMove1 = "r";
            otherMove2 = "s";
        } else {
            otherMove1 = "r";
            otherMove2 = "p";
        }
        if (temp == 0) {
            return otherMove1;
        } else if (temp == 1) {
            return otherMove2;
        } else {
            return slant;
        }
    }

    public String randomChoiceThatMightBeSlanted() {
        if (random.nextInt(2) == 1) {
            return randomMove();
        }
        String move = randomMove();
        for (int i = 0; i < random.nextInt(25); i++) {
            move = randomMove(move);
        }
        return move;
    }

    public String whateverBeatTheirLastMove() {
        return beatThis(getLastMove());
    }

    public String whateverLostTheirLastMove() {
        return loseThis(getLastMove());
    }

    public String getLastMove() {
        return pastMoves.get(pastMoves.size() - 1);
    }

    public String beatThis(String move) {
        if (move.equals("p")) {
            return "s";
        } else if (move.equals("r")) {
            return "p";
        } else if (move.equals("s")) {
            return "r";
        } else {
            return "p";
        }
    }

    public String loseThis(String move) {
        if (move.equals("p")) {
            return "r";
        } else if (move.equals("r")) {
            return "s";
        } else if (move.equals("s")) {
            return "p";
        } else {
            return "r";
        }
    }

    public String getMove() {
        if (random.nextInt(9) + 1 < this.diff_level) {
            if (random.nextInt(2) == 1) {
                if (!threeInARow && pastMoves.size() == 0) {
                    this.threeInARow = true;
                    this.threeMove = randomMove("p");
                }
                if (threeInARow && pastMoves.size() < 4) {
                    return this.threeMove;
                }
            }
            if (random.nextInt(2) == 1) {
                if (pastMoves.size() == 0) {
                    return randomChoiceThatMightBeSlanted();
                } else if (pastMoves.size() == 1) {
                    if (random.nextInt(3) < 2) {
                        return whateverBeatTheirLastMove();
                    }
                    return whateverLostTheirLastMove();
                } else if (pastMoves.size() > 10 && percentMove() >= 0.7) {
                    return beatThis(mostCommonMove());
                } else if (pastMoves.size() == 2 && percentMove() > 0.5) {
                    return beatThis(mostCommonMove());
                } else {
                    return randomMove("p");
                }
            } else {
                if (pastMoves.size() == 0) {
                    return randomChoiceThatMightBeSlanted();
                } else if (pastMoves.size() == 1) {
                    if (random.nextInt(2) == 0) {
                        return whateverLostTheirLastMove();
                    }
                    return whateverBeatTheirLastMove();
                } else if (pastMoves.size() > 10 && percentMove() >= 0.8) {
                    return beatThis(mostCommonMove());
                } else {
                    return randomMove("p");
                }
            }
        } else {
            return randomMove();
        }
    }

    public String mostCommonMove() {
        int rocks = 0;
        int sciss = 0;
        int papes = 0;
        for (String move : pastMoves) {
            if (move.equals("r")) {
                rocks++;
            } else if (move.equals("s")) {
                sciss++;
            } else if (move.equals("p")) {
                papes++;
            }
        }
        if (rocks > sciss && rocks > papes) {
            return "r";
        } else if (sciss > rocks && sciss > papes) {
            return "s";
        } else if (papes > sciss && papes > rocks) {
            return "p";
        } else if (rocks > sciss && papes > sciss && rocks == papes) {
            if (random.nextInt(2) == 1) {
                return "r";
            }
            return "p";
        } else if (sciss > rocks && papes > rocks && sciss == papes) {
            if (random.nextInt(2) == 1) {
                return "p";
            }
            return "s";
        } else if (sciss > papes && rocks > papes && sciss == rocks) {
            if (random.nextInt(2) == 1) {
                return "s";
            }
            return "r";
        } else {
            return randomMove();
        }
    }

    public int numMoves(String move) {
        int count = 0;
        for (String temp : pastMoves) {
            if (temp.equals(move)) {
                count++;
            }
        }
        return count;
    }

    public double percentMove(String move) {
        int numTimes = 1;
        for (String temp : pastMoves) {
            if (temp.equals(move)) {
                numTimes++;
            }
        }
        return (double) numTimes / pastMoves.size();
    }

    public double percentMove() {
        return percentMove(mostCommonMove());
    }
}


/*
 * The MIT License
 *
 * Copyright (c) 2018 Tim Barber.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
