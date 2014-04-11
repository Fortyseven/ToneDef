/*******************************************************************************
 * ________                 ____       ____
 * _/_  __/___  ____  ___  / __ \___  / __/
 * __/ / / __ \/ __ \/ _ \/ / / / _ \/ /_
 * _/ / / /_/ / / / /  __/ /_/ /  __/ __/
 * /_/  \____/_/ /_/\___/_____/\___/_/
 *
 * Copyright (c) 2014 Bytes Templar
 * http://BytesTemplar.com/
 *
 * Refer to the license.txt file included for license information.
 * If it is missing, contact fortyseven@gmail.com for details.
 ******************************************************************************/

package com.hacsoft.tonedef.tones;

/*
    This is an entry in a ToneBank definition. This object might represent a single, playable
    keypad tone, or a sequence of tones, such as a busy signal.
*/

//public class ToneBankEntry
//{
//    public char               ch;
//    public ToneSequence       sequence;
//    public SequenceDefinition definition;
//
//    public void start()
//    {
//        if ( definition.isCommand() ) {
//            definition.execute();
//        }
//        else {
//            sequence.start();
//        }
//    }
//
//    public void stop()
//    {
//        if ( !definition.isCommand() ) {
//            sequence.stop();
//        }
//    }
//}