package com.hngc.run;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * 背景音乐播放类
 * 用于控制游戏中的背景音乐播放、停止、循环等操作
 */
public class BackGroundMusic
{

    /**
     * 用于播放音频的Clip对象
     */
    private static Clip clip;

    /**
     * 音频总长度（微秒）
     */
    private static long audioTotalMicroseconds = 0;

    /**
     * 循环次数
     */
    private static int loopCount = 0;

    /**
     * 构造方法
     *
     * @param s 音频文件路径
     */
    public BackGroundMusic(String s)
    {
        playMusic(s);
    }

    /**
     * 播放背景音乐
     *
     * @param resourceName 音频文件路径
     */
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

            // 获取音频总长度（微秒）
            audioTotalMicroseconds = clip.getMicrosecondLength();
            loopCount = 0;

            clip.start(); // 开始播放
            clip.loop(Clip.LOOP_CONTINUOUSLY); // 循环播放
        }
        catch (Exception e)
        {
            System.err.println("Playback failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 停止背景音乐播放
     */
    public static void stopMusic()
    {
        if (clip != null && clip.isRunning())
        {
            clip.stop();
            clip.close(); // 释放系统资源
            clip = null;
            audioTotalMicroseconds = 0;
            loopCount = 0;
        }
    }

    /**
     * 获取当前播放位置（秒）
     *
     * @return 当前播放位置（秒）
     */
    public static double getCurrentPosition()
    {
        if (clip != null && clip.isRunning())
        {
            return clip.getMicrosecondPosition() / 1000000.0;
        }
        return 0.0;
    }

    /**
     * 获取音频总长度（秒）
     *
     * @return 音频总长度（秒）
     */
    public static double getTotalDuration()
    {
        if (audioTotalMicroseconds > 0)
        {
            return audioTotalMicroseconds / 1000000.0;
        }
        return 0.0;
    }

    /**
     * 获取剩余播放时间（秒）
     * 注意：对于无限循环的音频，剩余时间表示当前循环的剩余时间
     *
     * @return 剩余播放时间（秒）
     */
    public static double getRemainingTime()
    {
        if (clip != null && clip.isRunning() && audioTotalMicroseconds > 0)
        {
            long currentPos = clip.getMicrosecondPosition();
            return (audioTotalMicroseconds - currentPos) / 1000000.0;
        }
        return 0.0;
    }

    /**
     * 获取当前循环次数
     *
     * @return 当前循环次数
     */
    public static int getLoopCount()
    {
        if (clip != null && clip.isRunning() && audioTotalMicroseconds > 0)
        {
            long currentPos = clip.getMicrosecondPosition();
            return (int) (currentPos / audioTotalMicroseconds);
        }
        return 0;
    }

    /**
     * 获取播放状态信息
     *
     * @return 播放状态信息字符串
     */
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

    /**
     * 检查是否正在播放音乐
     *
     * @return 是否正在播放
     */
    public static boolean isPlaying()
    {
        return clip != null && clip.isRunning();
    }
}