package com.delts.shipitfixit;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.delts.shipitfixit.auth.Auth;
import com.delts.shipitfixit.database.UserInfoDBHelper;
import com.delts.shipitfixit.databinding.FragmentAccountInfoBinding;
import com.delts.shipitfixit.models.User;
import com.delts.shipitfixit.models.UserInfo;

import java.util.ArrayList;

public class AccountInfoFragment extends Fragment {

    FragmentAccountInfoBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAccountInfoBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        UserInfoDBHelper userInfoDBHelper = new UserInfoDBHelper(getContext());
        Auth auth = new Auth(getContext());
        final User user = auth.getSavedUser();
        final ArrayList<UserInfo> userInfos = userInfoDBHelper.getUserInfoArrayList(user.getUserName());


        for (UserInfo userInfo : userInfos){
            binding.usernameTextUserInfo.setText(userInfo.getUsername());
            binding.firstnameTextUserInfo.setText(userInfo.getFirstname());
            binding.lastnameTextUserInfo.setText(userInfo.getLastname());
            binding.birthdayTextUserInfo.setText(userInfo.getBirthday());
            binding.ageTextUserInfo.setText(Integer.toString(userInfo.getAge()));
            binding.genderTextUserInfo.setText(userInfo.getGender());
            binding.addressTextUserInfo.setText(userInfo.getAddress());
        }

        return view;
    }
}