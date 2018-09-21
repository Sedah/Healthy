package com.example.lab203_28.healthy;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class RegisterFragment extends Fragment{
    private FirebaseAuth mAuth;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);}
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initRegisterBtn();
    }
    void initRegisterBtn(){
        TextView _regBtn = (TextView) getView().findViewById(R.id.reg_register);
        _regBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                EditText _email = (EditText) getView().findViewById(R.id.reg_email);
                EditText _password = (EditText) getView().findViewById(R.id.reg_password);
                EditText _confirmPassword = (EditText) getView().findViewById(R.id.reg_re_password);
                String _emailStr = _email.getText().toString();
                String _passwordStr = _password.getText().toString();
                String _confirmPasswordStr = _confirmPassword.getText().toString();
                if (_emailStr.isEmpty() || _passwordStr.isEmpty() || _confirmPasswordStr.isEmpty()){
                    Toast.makeText(
                            getActivity(),
                            "กรุณาระบุข้อมูลให้ครบถ้วน",
                            Toast.LENGTH_SHORT
                    ).show();
                    Log.d("USER", "FIELD IS EMPTY");
                }

                else{
                if (_passwordStr.length() < 6) {
                    Toast.makeText(
                            getActivity(),"รหัสผ่านต้องมี 6 ตัวอักษรขึ้นไป", Toast.LENGTH_SHORT
                    ).show();
                    Log.d("USER", "LENGTH OF PASSWORD < 6");
                }
                else if (_passwordStr.equals(_confirmPasswordStr) != true) {
                    Toast.makeText(
                            getActivity(),"รหัสผ่านทั้งสองช่องไม่ตรงกัน", Toast.LENGTH_SHORT
                    ).show();
                    Log.d("REGISTER", "PASSWORD DOESN'T MATCH THE CONFIRM PASSWORD");
                }
                else {
                    Log.d("REGISTER", "PASSWORD IS CORRECT BY STATEMENT");
                    createAccount(_emailStr, _passwordStr);
                }
                }
            }
            void createAccount (String _emailStr, String _passwordStr) {
                mAuth = FirebaseAuth.getInstance();
                mAuth.createUserWithEmailAndPassword(_emailStr, _passwordStr)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                FirebaseUser _user = mAuth.getCurrentUser();
                                sendVerifiedEmail(_user);
                                Toast.makeText(
                                        getActivity(),"ทำการส่งอีเมลยืนยันแล้ว โปรดตรวจสอบอีเมล", Toast.LENGTH_SHORT
                                ).show();
                                Log.d("REGISTER", "SUCCESS TO SEND VERIFIED EMAIL AND CREATE USER");
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(
                                getActivity(), e.getMessage(), Toast.LENGTH_SHORT
                        ).show();
                        Log.d("REGISTER", e.getMessage());
                    }
                });
            }
            void sendVerifiedEmail (FirebaseUser _user) {
                _user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        mAuth.signOut();
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.main_view, new LoginFragment())
                                .addToBackStack(null)
                                .commit();
                        Log.d("REGISTER", "ALREADY SENT");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(
                                getActivity(),"Can't send a verified email", Toast.LENGTH_SHORT
                        ).show();
                        Log.d("REGISTER", "CAN'T SEND VERIFIED EMAIL");
                    }
                });
            }
        });
    }
}