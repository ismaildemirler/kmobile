package tr.com.deneme.kmobile.util;

import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AlignmentSpan;

public class TextHelper {
    public Spannable GetCenteredText(String text){
        String warning = Constants.USER_NOT_FOUND;
        Spannable centeredText = new SpannableString(warning);
        centeredText.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER),
                0, warning.length() - 1,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        return centeredText;
    }

    public boolean isEmpty(CharSequence str) {
        if (str == null || str.length() == 0)
            return true;
        else
            return false;
    }
}
