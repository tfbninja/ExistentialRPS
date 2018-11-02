package existentialrps;

import java.util.HashMap;

/**
 *
 * @author Tim Barber
 */
public class RPSEngine {

    private static HashMap<String, Integer> winMap = new HashMap();
    private String p1;
    private String p2;

    static {  // init winMap
        winMap.put("r p", 1);
        winMap.put("p r", 0);
        winMap.put("r s", 0);
        winMap.put("s r", 1);
        winMap.put("p s", 1);
        winMap.put("s p", 0);
    }

    public RPSEngine() {
        this.p1 = "";
        this.p2 = "";
    }

    public RPSEngine(String p1, String p2) {
        this.p1 = p1.substring(0, 1);
        this.p2 = p2.substring(0, 1);
    }

    public RPSEngine(char p1, char p2) {
        this.p1 = String.valueOf(p1);
        this.p2 = String.valueOf(p2);
    }

    public String getP1() {
        return this.p1;
    }

    public String getP2() {
        return this.p2;
    }

    public void setP1(String p1) {
        this.p1 = p1.substring(0, 1);
    }

    public void setP2(String p2) {
        this.p2 = p2.substring(0, 1);
    }

    public void setP1(char p1) {
        this.p1 = String.valueOf(p1);
    }

    public void setP2(char p2) {
        this.p2 = String.valueOf(p2);
    }

    public int getWinner() {
        String formatted = this.p1 + " " + this.p2;
        if (this.p1.equals(this.p2)) {
            return -1; // draw
        }
        return winMap.get(formatted);
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
