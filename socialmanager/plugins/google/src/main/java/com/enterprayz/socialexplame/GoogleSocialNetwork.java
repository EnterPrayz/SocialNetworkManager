package com.enterprayz.socialexplame;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.enterprayz.social.core.LoginListener;
import com.enterprayz.social.core.SocialNetwork;
import com.enterprayz.social.core.beans.NetworkTag;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

/**
 * Created by Fedir on 15.07.2016.
 */
public class GoogleSocialNetwork extends SocialNetwork {
    private GoogleSignInOptions gso;
    private static final int RC_SIGN_IN = 9001;
    private GoogleApiClient googleApiClient;
    private LoginListener listener;
    private boolean connected;
    @Override
    public NetworkTag getTag() {
        return NetworkTag.GOOGLE;
    }

    @Override
    public void getAccessToken(LoginListener listener) {
        this.listener = listener;
        if(!connected) {
            initLogin();
            connected = true;
        }
            signIn();
    }
    private void initLogin(){
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .requestId()
                .build();
        googleApiClient = new GoogleApiClient.Builder(getFragment().getContext())
                .enableAutoManage(getFragment().getActivity(), new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        getFragment().startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void signOut() {
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {

                    }
                });
    }


    private void revokeAccess() {
        Auth.GoogleSignInApi.revokeAccess(googleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {

                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(googleApiClient != null) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result != null && result.isSuccess() && requestCode == RC_SIGN_IN) {
                GoogleSignInAccount googleSignInAccount = result.getSignInAccount();
                listener.onGetAccessToken(NetworkTag.GOOGLE, googleSignInAccount.getId());
                signOut();
            }
        }
    }
}
