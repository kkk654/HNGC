package com.hngc.run;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class BackGroundMusic
{
    private Clip clip;
    private FloatControl volumeControl;
    
    // 构造函数，加载指定路径的音频文件
    public BackGroundMusic(String path)
    {
        try
        {
            // 获取音频输入流
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(path));
            
            // 创建音频剪辑
            clip = AudioSystem.getClip();
            
            // 打开音频剪辑并加载音频数据
            clip.open(audioInputStream);
            
            // 获取音量控制
            if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN))
            {
                volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            }
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e)
        {
            e.printStackTrace();
            System.err.println("音乐文件加载失败: " + path);
        }
    }
    
    // 播放音乐
    public void play()
    {
        if (clip != null)
        {
            // 如果已经在播放，先停止
            if (clip.isRunning())
            {
                clip.stop();
            }
            // 重置到开始位置
            clip.setFramePosition(0);
            // 开始播放
            clip.start();
        }
    }
    
    // 循环播放音乐
    public void loop()
    {
        if (clip != null)
        {
            // 如果已经在播放，先停止
            if (clip.isRunning())
            {
                clip.stop();
            }
            // 重置到开始位置
            clip.setFramePosition(0);
            // 循环播放
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }
    
    // 停止播放音乐
    public void stop()
    {
        if (clip != null && clip.isRunning())
        {
            clip.stop();
        }
    }
    
    // 关闭音频资源
    public void close()
    {
        if (clip != null)
        {
            clip.stop();
            clip.close();
        }
    }
    
    // 设置音量 (0.0 到 1.0)
    public void setVolume(float volume)
    {
        if (volumeControl != null)
        {
            // 将 0.0-1.0 的范围转换为分贝范围
            float min = volumeControl.getMinimum();
            float max = volumeControl.getMaximum();
            float gain = min + (max - min) * Math.min(1.0f, Math.max(0.0f, volume));
            volumeControl.setValue(gain);
        }
    }
    
    // 获取当前播放状态
    public boolean isPlaying()
    {
        return clip != null && clip.isRunning();
    }
}