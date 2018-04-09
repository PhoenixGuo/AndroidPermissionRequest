package fightcent.permissionrequest;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;


/**
 * Created by andy.guo on 2016/6/22.
 * 权限设置弹出框
 */
public class PermissionRequestDialog {

    public static AlertDialog makeDialog(
            @NonNull final Activity activity,
            @Nullable final DialogInterface.OnClickListener negativeListener,
            @StringRes int titleRes,
            @StringRes int messageRes,
            @StringRes int positiveRes,
            @StringRes int negativeRes
    ) {
        AlertDialog dialog = PositiveNegativeDialog.makeDialog(
                activity,
                false,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent myAppSettings = new Intent(
                                Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                Uri.parse("package:" + activity.getPackageName())
                        );
                        myAppSettings.addCategory(Intent.CATEGORY_DEFAULT);
                        myAppSettings.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        activity.startActivity(myAppSettings);
                    }
                },
                negativeListener,
                titleRes,
                positiveRes,
                negativeRes,
                messageRes
        );
        return dialog;
    }

}
