package net.avantica.xinef.dapp.exception;

import android.content.Context;

import net.avantica.xinef.dapp.R;
import net.avantica.xinef.dapp.data.exception.NetworkConnectionException;
import net.avantica.xinef.dapp.data.exception.PublicInvestmentProjectNotFoundException;
import net.avantica.xinef.dapp.data.exception.ReverseGeocodingNotFoundException;

import org.json.JSONException;

import java.net.SocketTimeoutException;

/**
 * Factory used to create error messages from an Exception as a condition.
 */
public class ErrorMessageFactory {

    private ErrorMessageFactory() {
        //empty
    }

    /**
     * Creates a String representing an error message.
     *
     * @param context   Context needed to retrieve string resources.
     * @param exception An exception used as a condition to retrieve the correct error message.
     * @return {@link String} an error message.
     */
    public static String create(Context context, Exception exception) {
        String message = context.getString(R.string.exception_message_generic);

        if (exception instanceof NetworkConnectionException) {
            message = context.getString(R.string.exception_message_no_connection);
        } else if (exception instanceof PublicInvestmentProjectNotFoundException) {
            message = context.getString(R.string.exception_message_public_investment_project_not_found);
        } else if (exception instanceof JSONException) {
            message = context.getString(R.string.exception_message_there_was_a_error_in_service);
        } else if (exception instanceof ReverseGeocodingNotFoundException) {
            message = context.getString(R.string.exception_message_cannot_get_information_of_your_ubication);
        } else if (exception instanceof SocketTimeoutException) {
            message = context.getString(R.string.exception_message_time_out);
        }

        return message;
    }
}
