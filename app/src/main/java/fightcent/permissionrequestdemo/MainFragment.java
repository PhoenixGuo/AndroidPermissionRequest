package fightcent.permissionrequestdemo;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import fightcent.permissionrequest.PermissionRequest;
import fightcent.permissionrequest.PermissionRequestListener;

/**
 * Created by andy.guo on 2018/4/9.
 */

public class MainFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_main, null);
        String[] permissions = new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
                Manifest.permission.CALL_PHONE
        };
        new PermissionRequest(
                getActivity()
        ).request(
                permissions,
                new PermissionRequestListener() {
                    @Override
                    public void onHasPermissions() {
                        Toast toast = Toast.makeText(getContext(), "有权限", Toast.LENGTH_SHORT);
                        toast.show();
                    }

                    @Override
                    public void onHasNotPermissions() {
                        Toast toast = Toast.makeText(getContext(), "没权限", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
        );
        return view;
    }

}
