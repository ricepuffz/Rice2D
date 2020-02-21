package de.ricepuffz.rice2d.input;

import java.util.HashMap;
import java.util.Map;

import org.lwjgl.glfw.GLFW;

import de.ricepuffz.rice2d.References;

public class InputManager
{	
	// Printable keys
    public static final int
        KEY_SPACE         = 32,		//0
        KEY_APOSTROPHE    = 39,		//1
        KEY_COMMA         = 44,		//2
        KEY_MINUS         = 45,		//3
        KEY_PERIOD        = 46,		//4
        KEY_SLASH         = 47,		//5
        KEY_0             = 48,		//6
        KEY_1             = 49,		//7
        KEY_2             = 50,		//8
        KEY_3             = 51,		//9
        KEY_4             = 52,		//10
        KEY_5             = 53,		//11
        KEY_6             = 54,		//12
        KEY_7             = 55,		//13
        KEY_8             = 56,		//14
        KEY_9             = 57,		//15
        KEY_SEMICOLON     = 59,		//16
        KEY_EQUAL         = 61,		//17
        KEY_A             = 65,		//18
        KEY_B             = 66,		//19
        KEY_C             = 67,		//20
        KEY_D             = 68,		//21
        KEY_E             = 69,		//22
        KEY_F             = 70,		//23
        KEY_G             = 71,		//24
        KEY_H             = 72,		//25
        KEY_I             = 73,		//26
        KEY_J             = 74,		//27
        KEY_K             = 75,		//28
        KEY_L             = 76,		//29
        KEY_M             = 77,		//30
        KEY_N             = 78,		//31
        KEY_O             = 79,		//32
        KEY_P             = 80,		//33
        KEY_Q             = 81,		//34
        KEY_R             = 82,		//35
        KEY_S             = 83,		//36
        KEY_T             = 84,		//37
        KEY_U             = 85,		//38
        KEY_V             = 86,		//39
        KEY_W             = 87,		//40
        KEY_X             = 88,		//41
        KEY_Y             = 89,		//42
        KEY_Z             = 90,		//43
        KEY_LEFT_BRACKET  = 91,		//44
        KEY_BACKSLASH     = 92,		//45
        KEY_RIGHT_BRACKET = 93,		//46
        KEY_GRAVE_ACCENT  = 96,		//47
        KEY_WORLD_1       = 161,	//48
        KEY_WORLD_2       = 162;	//49

    //Function keys.
    public static final int
        KEY_ESCAPE        = 256,	//50
        KEY_ENTER         = 257,	//51
        KEY_TAB           = 258,	//52
        KEY_BACKSPACE     = 259,	//53
        KEY_INSERT        = 260,	//54
        KEY_DELETE        = 261,	//55
        KEY_RIGHT         = 262,	//56
        KEY_LEFT          = 263,	//57
        KEY_DOWN          = 264,	//58
        KEY_UP            = 265,	//59
        KEY_PAGE_UP       = 266,	//60
        KEY_PAGE_DOWN     = 267,	//61
        KEY_HOME          = 268,	//62
        KEY_END           = 269,	//63
        KEY_CAPS_LOCK     = 280,	//64
        KEY_SCROLL_LOCK   = 281,	//65
        KEY_NUM_LOCK      = 282,	//66
        KEY_PRINT_SCREEN  = 283,	//67
        KEY_PAUSE         = 284,	//68
        KEY_F1            = 290,	//69
        KEY_F2            = 291,	//70
        KEY_F3            = 292,	//71
        KEY_F4            = 293,	//72
        KEY_F5            = 294,	//73
        KEY_F6            = 295,	//74
        KEY_F7            = 296,	//75
        KEY_F8            = 297,	//76
        KEY_F9            = 298,	//77
        KEY_F10           = 299,	//78
        KEY_F11           = 300,	//79
        KEY_F12           = 301,	//80
        KEY_F13           = 302,	//81
        KEY_F14           = 303,	//82
        KEY_F15           = 304,	//83
        KEY_F16           = 305,	//84
        KEY_F17           = 306,	//85
        KEY_F18           = 307,	//86
        KEY_F19           = 308,	//87
        KEY_F20           = 309,	//88
        KEY_F21           = 310,	//89
        KEY_F22           = 311,	//90
        KEY_F23           = 312,	//91
        KEY_F24           = 313,	//92
        KEY_F25           = 314,	//93
        KEY_KP_0          = 320,	//94
        KEY_KP_1          = 321,	//95
        KEY_KP_2          = 322,	//96
        KEY_KP_3          = 323,	//97
        KEY_KP_4          = 324,	//98
        KEY_KP_5          = 325,	//99
        KEY_KP_6          = 326,	//100
        KEY_KP_7          = 327,	//101
        KEY_KP_8          = 328,	//102
        KEY_KP_9          = 329,	//103
        KEY_KP_DECIMAL    = 330,	//104
        KEY_KP_DIVIDE     = 331,	//105
        KEY_KP_MULTIPLY   = 332,	//106
        KEY_KP_SUBTRACT   = 333,	//107
        KEY_KP_ADD        = 334,	//108
        KEY_KP_ENTER      = 335,	//109
        KEY_KP_EQUAL      = 336,	//110
        KEY_LEFT_SHIFT    = 340,	//111
        KEY_LEFT_CONTROL  = 341,	//112
        KEY_LEFT_ALT      = 342,	//113
        KEY_LEFT_SUPER    = 343,	//114
        KEY_RIGHT_SHIFT   = 344,	//115
        KEY_RIGHT_CONTROL = 345,	//116
        KEY_RIGHT_ALT     = 346,	//117
        KEY_RIGHT_SUPER   = 347,	//118
        KEY_MENU          = 348;	//119
    
    // Mouse buttons
    public static final int
        MOUSE_BUTTON_1      = 0,	//120
        MOUSE_BUTTON_2      = 1,	//121
        MOUSE_BUTTON_3      = 2,	//122
        MOUSE_BUTTON_4      = 3,	//123
        MOUSE_BUTTON_5      = 4,	//124
        MOUSE_BUTTON_6      = 5,	//125
        MOUSE_BUTTON_7      = 6,	//126
        MOUSE_BUTTON_8      = 7,	//127
        MOUSE_BUTTON_LEFT   = MOUSE_BUTTON_1,	//128
        MOUSE_BUTTON_RIGHT  = MOUSE_BUTTON_2,	//129
        MOUSE_BUTTON_MIDDLE = MOUSE_BUTTON_3;	//130
    
    int[] values = new int[] {
			KEY_SPACE,
	        KEY_APOSTROPHE,
	        KEY_COMMA,
	        KEY_MINUS,
	        KEY_PERIOD,
	        KEY_SLASH,
	        KEY_0,
	        KEY_1,
	        KEY_2,
	        KEY_3,
	        KEY_4,
	        KEY_5,
	        KEY_6,
	        KEY_7,
	        KEY_8,
	        KEY_9,
	        KEY_SEMICOLON,
	        KEY_EQUAL,
	        KEY_A,
	        KEY_B,
	        KEY_C,
	        KEY_D,
	        KEY_E,
	        KEY_F,
	        KEY_G,
	        KEY_H,
	        KEY_I,
	        KEY_J,
	        KEY_K,
	        KEY_L,
	        KEY_M,
	        KEY_N,
	        KEY_O,
	        KEY_P,
	        KEY_Q,
	        KEY_R,
	        KEY_S,
	        KEY_T,
	        KEY_U,
	        KEY_V,
	        KEY_W,
	        KEY_X,
	        KEY_Y,
	        KEY_Z,
	        KEY_LEFT_BRACKET,
	        KEY_BACKSLASH,
	        KEY_RIGHT_BRACKET,
	        KEY_GRAVE_ACCENT,
	        KEY_WORLD_1,
	        KEY_WORLD_2,
	        KEY_ESCAPE,
	        KEY_ENTER,
	        KEY_TAB,
	        KEY_BACKSPACE,
	        KEY_INSERT,
	        KEY_DELETE,
	        KEY_RIGHT,
	        KEY_LEFT,
	        KEY_DOWN,
	        KEY_UP,
	        KEY_PAGE_UP,
	        KEY_PAGE_DOWN,
	        KEY_HOME,
	        KEY_END,
	        KEY_CAPS_LOCK,
	        KEY_SCROLL_LOCK,
	        KEY_NUM_LOCK,
	        KEY_PRINT_SCREEN,
	        KEY_PAUSE,
	        KEY_F1,
	        KEY_F2,
	        KEY_F3,
	        KEY_F4,
	        KEY_F5,
	        KEY_F6,
	        KEY_F7,
	        KEY_F8,
	        KEY_F9,
	        KEY_F10,
	        KEY_F11,
	        KEY_F12,
	        KEY_F13,
	        KEY_F14,
	        KEY_F15,
	        KEY_F16,
	        KEY_F17,
	        KEY_F18,
	        KEY_F19,
	        KEY_F20,
	        KEY_F21,
	        KEY_F22,
	        KEY_F23,
	        KEY_F24,
	        KEY_F25,
	        KEY_KP_0,
	        KEY_KP_1,
	        KEY_KP_2,
	        KEY_KP_3,
	        KEY_KP_4,
	        KEY_KP_5,
	        KEY_KP_6,
	        KEY_KP_7,
	        KEY_KP_8,
	        KEY_KP_9,
	        KEY_KP_DECIMAL,
	        KEY_KP_DIVIDE,
	        KEY_KP_MULTIPLY,
	        KEY_KP_SUBTRACT,
	        KEY_KP_ADD,
	        KEY_KP_ENTER,
	        KEY_KP_EQUAL,
	        KEY_LEFT_SHIFT,
	        KEY_LEFT_CONTROL,
	        KEY_LEFT_ALT,
	        KEY_LEFT_SUPER,
	        KEY_RIGHT_SHIFT,
	        KEY_RIGHT_CONTROL,
	        KEY_RIGHT_ALT,
	        KEY_RIGHT_SUPER,
	        KEY_MENU,
	        MOUSE_BUTTON_1,
	        MOUSE_BUTTON_2,
	        MOUSE_BUTTON_3,
	        MOUSE_BUTTON_4,
	        MOUSE_BUTTON_5,
	        MOUSE_BUTTON_6,
	        MOUSE_BUTTON_7,
	        MOUSE_BUTTON_8
	};
    
    
	long window = -1;
	
	private boolean[] pressedLastFrame = null;
	
	//Value to pressedLastFrame index map
	Map<Integer, Integer> valueToIndexMap = null;
   
	
    public InputManager(long window)
    {
    	this.window = window;
    	pressedLastFrame = new boolean[127];
    	initIndexMap();
    }
    
    
    public void updatePressedLastFrame()
    {
    	for (int i = 0; i < pressedLastFrame.length - 8; i++)
    		pressedLastFrame[i] = GLFW.glfwGetKey(window, values[i]) == GLFW.GLFW_TRUE ? true : false;
    	for (int i = pressedLastFrame.length - 8; i < pressedLastFrame.length; i++)
    		pressedLastFrame[i] = GLFW.glfwGetMouseButton(window, values[i]) == GLFW.GLFW_TRUE ? true : false;
    }
    
    
    private boolean getKey(int key)
    {
    	return GLFW.glfwGetKey(window, key) == GLFW.GLFW_TRUE ? true : false;
    }
    private boolean getMouseButton(int button)
    {
    	return GLFW.glfwGetMouseButton(window, button) == GLFW.GLFW_TRUE ? true : false;
    }
    
    public boolean getPressed(int key)
	{
		if (key > 32)
			return getKey(key);
		else
			return getMouseButton(key);
	}
	public boolean getPressStart(int key)
	{
		if (key > 32)
			return getKey(key) && !getPressedLastFrame(key);
		else
			return getMouseButton(key) && !getPressedLastFrame(key);
	}
    public boolean getPressedLastFrame(int key)
    {
    	return pressedLastFrame[valueToIndexMap.get(key)];
    }
    
    
    
    
    private void initIndexMap()
    {
    	valueToIndexMap = new HashMap<Integer, Integer>();
    	
    	for (int i = 0; i < values.length; i++)
    		valueToIndexMap.put(values[i], i);
    }
    
    
    public double[] getCursorPos()
    {
    	double[] xpos = new double[1];
    	double[] ypos = new double[1];
    	
    	GLFW.glfwGetCursorPos(window, xpos, ypos);
    	
    	return new double[] { xpos[0], ypos[0] };
    }
    
    public double[] getCursorPosFromMiddle()
    {
    	double[] cursorPos = getCursorPos();
    	
    	int width = References.window.getWidth();
    	int height = References.window.getHeight();
    	
    	return new double[] { cursorPos[0] - width / 2, -cursorPos[1] + height / 2 };
    }
    
    public void setCursorPos(double x, double y)
    {
    	GLFW.glfwSetCursorPos(window, x, y);
    }
}















