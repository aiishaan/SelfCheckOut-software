package com.autovend.software.controllers;

import com.autovend.devices.AbstractDevice;
import com.autovend.devices.Keyboard;
import com.autovend.devices.observers.AbstractDeviceObserver;
import com.autovend.devices.observers.KeyboardObserver;

public class KeyBoardController extends DeviceController<Keyboard, KeyboardObserver> implements KeyboardObserver {
    String input = new String();
    public KeyBoardController(Keyboard newDevice) {
        super(newDevice);
    }
    private CheckoutController mainController;

    final CheckoutController getMainController() {
        return this.mainController;
    };

    final void setMainController(CheckoutController newMainController) {
//        if (this.mainController != null) {
//            this.mainController.deregisterChangeSlotController(this);
//        }
//        ;
//        this.mainController = newMainController;
//        if (this.mainController != null) {
//            this.mainController.registerChangeSlotController(this);
//        }
//        ;
    }
    @Override
    public void reactToKeyPressedEvent(Keyboard k, char c) {
        input = input+c;
    }
}
