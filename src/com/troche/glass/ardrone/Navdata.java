package com.troche.glass.ardrone;

/**
 * Created by Jose Troche on 10/12/2013.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Navdata associated with the ARDrone
 */
public class Navdata {
    public boolean isReceivingData = false;

    // Basic info
    public int state;
    public int sequence;
    public int visionFlag;

    // Demo navdata
    public short flyState;
    public short controlState;
    public int batteryPercentage;
    public float pitch;  // Pitch in milli-degrees
    public float roll;    // Roll in milli-degrees
    public float yaw;    // Yaw in milli-degrees
    public int altitude; // cm
    public int velocityX;
    public int velocityY;
    public int velocityZ;

    public Navdata(){
        //startNavdataReader();
    }

    public void startNavdataReader(){
        isReceivingData = true;
        new NavdataReaderTask().execute(this);
    }

    public boolean isFlying(){
        return getStateFlag(StateFlag.FLY) == 1;
    }

    public boolean isFlashDriveReady(){
        return getStateFlag(StateFlag.USB) == 1;
    }

    public boolean isBatteryLow(){
        return getStateFlag(StateFlag.VBAT_LOW) == 1;
    }

    public boolean isInEmergencyMode(){
        return getStateFlag(StateFlag.EMERGENCY) == 1;
    }

    public int getStateFlag(StateFlag flag){
        return ( state >> flag.ordinal() ) & 1;
    }

    private enum StateFlag{
        FLY, /* FLY : (0) ardrone is landed, (1) ardrone is flying */
        VIDEO,  /* VIDEO : (0) video disable, (1) video enable */
        VISION,  /* VISION : (0) vision disable, (1) vision enable */
        CONTROL,  /* CONTROL ALGO : (0) euler angles control, (1) angular speed control */
        ALTITUDE,  /* ALTITUDE CONTROL ALGO : (0) altitude control inactive (1) altitude control active */
        USER_FEEDBACK_START,  /* USER feedback : Start button state */
        COMMAND_CONTROL_ACK,  /* Control command ACK : (0) None, (1) one received */
        CAMERA,  /* CAMERA : (0) camera not ready, (1) Camera ready */
        TRAVELLING,  /* Travelling : (0) disable, (1) enable */
        USB,  /* USB key : (0) usb key not ready, (1) usb key ready */
        NAVDATA_DEMO, /* Navdata demo : (0) All navdata, (1) only navdata demo */
        NAVDATA_BOOTSTRAP, /* Navdata bootstrap : (0) options sent in all or demo mode, (1) no navdata options sent */
        MOTORS, /* Motors status : (0) Ok, (1) Motors problem */
        COM_LOST, /* Communication Lost : (1) com problem, (0) Com is ok */
        SOFTWARE_FAULT, /* Software fault detected - user should land as quick as possible (1) */
        VBAT_LOW, /* VBat low : (1) too low, (0) Ok */
        USER_EL, /* User Emergency Landing : (1) User EL is ON, (0) User EL is OFF*/
        TIMER_ELAPSED, /* Timer elapsed : (1) elapsed, (0) not elapsed */
        MAGNETO_NEEDS_CALIB, /* Magnetometer calibration state : (0) Ok, no calibration needed, (1) not ok, calibration needed */
        ANGLES_OUT_OF_RANGE, /* Angles : (0) Ok, (1) out of range */
        WIND, /* WIND: (0) ok, (1) Too much wind */
        ULTRASOUND, /* Ultrasonic sensor : (0) Ok, (1) deaf */
        CUTOUT, /* Cutout system detection : (0) Not detected, (1) detected */
        PIC_VERSION, /* PIC Version number OK : (0) a bad version number, (1) version number is OK */
        ATCODEC_THREAD_ON, /* ATCodec thread ON : (0) thread OFF (1) thread ON */
        NAVDATA_THREAD_ON, /* Navdata thread ON : (0) thread OFF (1) thread ON */
        VIDEO_THREAD_ON, /* Video thread ON : (0) thread OFF (1) thread ON */
        ACQ_THREAD_ON, /* Acquisition thread ON : (0) thread OFF (1) thread ON */
        CTRL_WATCHDOG, /* CTRL watchdog : (1) delay in control execution (> 5ms), (0) control is well scheduled */
        ADC_WATCHDOG9, /* ADC Watchdog : (1) delay in uart2 dsr (> 5ms), (0) uart2 is good */
        COM_WATCHDOG, /* Communication Watchdog : (1) com problem, (0) Com is ok */
        EMERGENCY /* Emergency landing : (0) no emergency, (1) emergency */
    }

}