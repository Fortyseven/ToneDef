/*******************************************************************************
 * ________                 ____       ____
 * _/_  __/___  ____  ___  / __ \___  / __/
 * __/ / / __ \/ __ \/ _ \/ / / / _ \/ /_
 * _/ / / /_/ / / / /  __/ /_/ /  __/ __/
 * /_/  \____/_/ /_/\___/_____/\___/_/
 *
 * Copyright (c) 2015 Bytes Templar
 * http://BytesTemplar.com/
 *
 * Refer to the license.txt file included for license information.
 * If it is missing, contact fortyseven@gmail.com for details.
 ******************************************************************************/

package com.bytestemplar.tonedef.tones;

/*
    This is an entry in a ToneBank _definition. This object might represent a single, playable
    keypad tone, or a _sequence of tones, such as a busy signal.
*/

//public class ToneBankEntry
//{
//    public char               _ch;
//    public ToneSequence       _sequence;
//    public SequenceDefinition _definition;
//
//    public void start()
//    {
//        if ( _definition.isCommand() ) {
//            _definition.execute();
//        }
//        else {
//            _sequence.start();
//        }
//    }
//
//    public void stop()
//    {
//        if ( !_definition.isCommand() ) {
//            _sequence.stop();
//        }
//    }
//}