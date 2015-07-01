/**
 * ****************************************************************************
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
 * ****************************************************************************
 */

package com.bytestemplar.tonedef.gen;

public class SequenceDefinition
{
    private int     _duration    = 0;
    private int[]   _frequencies = null;
    private Command _cmd         = null;

    public interface Command
    {
        void execute();
    }

    /**
     * Defines a tone and it's duration. This is used in both the playback of dialing sequencies, and for button presses.
     *
     * @param duration    How long this tone will be played for in dialing sequences, in milliseconds.
     * @param frequencies Frequencies of the tone, in hz. (Variable number of arguments.)
     */
    public SequenceDefinition(int duration, int... frequencies)
    {
        setDuration(duration);
        setFrequencies(frequencies);
    }

    /**
     * If invoked with a Command object, will execute a user-supplied function instead of
     * playing a tone.  Used in ToneBankRedBox for one-shot keys.
     *
     * @param cmd Command object to invoke instead of tone
     */
    public SequenceDefinition(Command cmd)
    {
        _cmd = cmd;
    }

    /**
     * Executes the user-defined function.
     */
    public void execute()
    {
        _cmd.execute();
    }

    /**
     * Returns the "on" duration of the sequence.
     *
     * @return Time in milliseconds
     */
    public int getDuration()
    {
        return _duration;
    }


    /**
     * Sets the "on" duration of the sequence.
     *
     * @param duration Time in milliseconds
     */
    public void setDuration(int duration)
    {
        this._duration = duration;
    }

    /**
     * Return an array of all of the frequencies defined in the sequence
     *
     * @return Returns an array of frequencies in Hz.
     */
    public int[] getFrequencies()
    {
        return _frequencies;
    }

    /**
     * Set the frequencies for use in the sequence.
     *
     * @param m_frequencies An array of integers representing tones in Hz.
     */
    public void setFrequencies(int[] m_frequencies)
    {
        this._frequencies = m_frequencies;
    }

    /**
     * This method is it's own documentation.
     *
     * @return Returns true if sequence is a command.
     */
    public boolean isCommand()
    {
        return (_cmd != null);
    }
}
