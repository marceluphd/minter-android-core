/*
 * Copyright (C) by MinterTeam. 2018
 * @link https://github.com/MinterTeam
 *
 * The MIT License
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
// $Id: Keccak256.java 189 2010-05-14 21:21:46Z tp $

package network.minter.core.crypto.cryptohash;

public class Keccak256 extends KeccakCore {

    /**
     * Create the engine.
     */
    public Keccak256() {
        super("eth-keccak-256");
    }

    /**
     * @see Digest
     */
    public Digest copy() {
        return copyState(new Keccak256());
    }

    /**
     * @see network.minter.core.crypto.cryptohash.Digest
     */
    public int engineGetDigestLength() {
        return 32;
    }

    @Override
    protected void engineUpdate(byte arg0) {
    }

    @Override
    protected void engineUpdate(byte[] arg0, int arg1, int arg2) {
    }

    @Override
    protected byte[] engineDigest() {
        return null;
    }
}
