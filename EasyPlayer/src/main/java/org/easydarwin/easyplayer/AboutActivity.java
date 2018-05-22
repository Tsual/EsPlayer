package org.easydarwin.easyplayer;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;

import org.esaydarwin.rtsp.player.BuildConfig;
import org.esaydarwin.rtsp.player.R;
import org.esaydarwin.rtsp.player.databinding.ActivityAboutBinding;

public class AboutActivity extends AppCompatActivity {

    private ActivityAboutBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         binding = DataBindingUtil.setContentView(this, R.layout.activity_about);

        setSupportActionBar(binding.toolbar);
        if (PlaylistActivity.isPro()){
            SpannableString spannableString = new SpannableString("support@easydarwin.org");
            //设置下划线文字
            spannableString.setSpan(new URLSpan("mailto:support@easydarwin.org"), 0, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

            //设置文字的前景色
            spannableString.setSpan(new ForegroundColorSpan(Color.RED), 0, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            spannableString = new SpannableString("戳我");
            //设置下划线文字
            spannableString.setSpan(new URLSpan("https://fir.im/EasyPlayer"), 0, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

            //设置文字的前景色
            spannableString.setSpan(new ForegroundColorSpan(Color.RED), 0, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        }else{
            SpannableString spannableString = new SpannableString("https://github.com/EasyDarwin/EasyPlayer");
            //设置下划线文字
            spannableString.setSpan(new URLSpan("https://github.com/EasyDarwin/EasyPlayer"), 0, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

            //设置文字的前景色
            spannableString.setSpan(new ForegroundColorSpan(Color.RED), 0, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            spannableString = new SpannableString("戳我");
            //设置下划线文字
            spannableString.setSpan(new URLSpan("https://fir.im/EasyPlayerPro"), 0, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

            //设置文字的前景色
            spannableString.setSpan(new ForegroundColorSpan(Color.RED), 0, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        }
    }
}
