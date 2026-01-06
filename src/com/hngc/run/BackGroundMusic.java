package com.hngc.run;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class BackGroundMusic
{
    private static Clip clip; // Use Clip class to play audio 
    private static long audioTotalMicroseconds = 0; // Audio total length (microseconds) 
    private static int loopCount = 0; // Loop count 


    public BackGroundMusic(String s)
    {
        playMusic(s);
    }


    public static void playMusic(String resourceName)
    {
        try
        {
            // 使用文件路径方式加载音频资源
            File audioFile = new File(resourceName);
            // 检查资源是否存在
            if (!audioFile.exists())
            {
                System.err.println("Resource not found: " + resourceName + ", Full path: " + audioFile.getAbsolutePath());
                return;
            }
            // 获取音频输入流
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            // 创建Clip对象
            clip = AudioSystem.getClip();
            // 打开音频输入流
            clip.open(audioStream);

            // Get audio total length (microseconds) 
            audioTotalMicroseconds = clip.getMicrosecondLength();
            loopCount = 0;

            clip.start(); // Start playing 
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Loop playback 
        }
        catch (Exception e)
        {
            System.err.println("Playback failed: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public static void stopMusic()
    {
        if (clip != null && clip.isRunning())
        {
            clip.stop();
            clip.close(); // Release system resources 
            clip = null;
            audioTotalMicroseconds = 0;
            loopCount = 0;
        }
    }


    // Get current playback position (seconds) 
    public static double getCurrentPosition()
    {
        if (clip != null && clip.isRunning())
        {
            return clip.getMicrosecondPosition() / 1000000.0;
        }
        return 0.0;
    }


    // Get audio total length (seconds) 
    public static double getTotalDuration()
    {
        if (audioTotalMicroseconds > 0)
        {
            return audioTotalMicroseconds / 1000000.0;
        }
        return 0.0;
    }


    // Get remaining time (for loop playback, this value changes continuously) 
    // Note: For infinitely looping audio, remaining time represents remaining time in current loop 
    public static double getRemainingTime()
    {
        if (clip != null && clip.isRunning() && audioTotalMicroseconds > 0)
        {
            long currentPos = clip.getMicrosecondPosition();
            return (audioTotalMicroseconds - currentPos) / 1000000.0;
        }
        return 0.0;
    }


    // Get current loop count 
    public static int getLoopCount()
    {
        if (clip != null && clip.isRunning() && audioTotalMicroseconds > 0)
        {
            long currentPos = clip.getMicrosecondPosition();
            return (int) (currentPos / audioTotalMicroseconds);
        }
        return 0;
    }


    // Get playback status information 
    public static String getStatusInfo()
    {
        if (clip == null)
        {
            return "Not playing";
        }

        if (!clip.isRunning())
        {
            return "Stopped";
        }

        double current = getCurrentPosition();
        double total = getTotalDuration();
        int loop = getLoopCount();

        return String.format("Current time: %.2fs / %.2fs, Loop count: %d", current, total, loop);
    }


    // Check if currently playing 
    public static boolean isPlaying()
    {
        return clip != null && clip.isRunning();
    }

}