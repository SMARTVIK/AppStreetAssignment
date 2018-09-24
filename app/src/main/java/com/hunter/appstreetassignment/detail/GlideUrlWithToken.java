package com.hunter.appstreetassignment.detail;

import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.util.Preconditions;

public class GlideUrlWithToken extends GlideUrl {
    private String mSourceUrl;

    public GlideUrlWithToken(String url, String token) {
        super(new StringBuilder(url)
                .append(url.contains("?") ? "&token=" : "?token=") //already has other parameters
                .append(token) // append the token at the end of url
                .toString());

        Preconditions.checkNotNull(url);
        Preconditions.checkNotEmpty(url);

        Preconditions.checkNotNull(token);
        Preconditions.checkNotEmpty(token);

        mSourceUrl = url;
    }

    @Override
    public String getCacheKey() {
        return mSourceUrl;
    }

    @Override
    public String toString() {
        return super.getCacheKey();
    }
}