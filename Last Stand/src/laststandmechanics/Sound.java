package laststandmechanics;

import java.applet.*;

public class Sound
{
    public static final AudioClip MISSILESHOT;
    public static final AudioClip ALIENHIT;
    public static final AudioClip CRAFTDEATH;
    public static final AudioClip MAINMUSIC;
    
    static 
    {
        MISSILESHOT = Applet.newAudioClip(Sound.class.getResource("Assets/Sounds/missileShot.wav"));
        ALIENHIT    = Applet.newAudioClip(Sound.class.getResource("Assets/Sounds/alienHit.wav"));
        CRAFTDEATH  = Applet.newAudioClip(Sound.class.getResource("Assets/Sounds/craftDeath.wav"));
        MAINMUSIC   = Applet.newAudioClip(Sound.class.getResource("Assets/Sounds/lastStandMusic.wav"));
    }
}