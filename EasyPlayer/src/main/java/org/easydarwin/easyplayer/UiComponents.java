package org.easydarwin.easyplayer;


import android.app.AlertDialog;
import android.content.Context;

public class UiComponents {
    public static void Alert(String msg, Context ctx) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setMessage(msg);
        builder.setPositiveButton("是", null);
        builder.show();
    }
}
