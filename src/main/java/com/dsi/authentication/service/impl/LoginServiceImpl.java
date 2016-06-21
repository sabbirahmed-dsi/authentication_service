package com.dsi.authentication.service.impl;

import com.dsi.authentication.dao.LoginDao;
import com.dsi.authentication.dao.impl.LoginDaoImpl;
import com.dsi.authentication.exception.CustomException;
import com.dsi.authentication.exception.ErrorContext;
import com.dsi.authentication.exception.ErrorMessage;
import com.dsi.authentication.model.Login;
import com.dsi.authentication.service.LoginService;
import com.dsi.authentication.util.Constants;

/**
 * Created by sabbir on 6/16/16.
 */
public class LoginServiceImpl implements LoginService {

    private static final LoginDao dao = new LoginDaoImpl();

    @Override
    public void updateLoginInfo(Login login) throws CustomException {
        boolean res = dao.updateLoginInfo(login);
        if(!res){
            ErrorContext errorContext = new ErrorContext(null, "Login", "Login info update failed.");
            ErrorMessage errorMessage = new ErrorMessage(Constants.AUTHENTICATE_SERVICE_0003,
                    Constants.AUTHENTICATE_SERVICE_0003_DESCRIPTION, errorContext);
            throw new CustomException(errorMessage);
        }
    }

    @Override
    public Login getLoginInfo(String userID, String email) throws CustomException {
        Login login = dao.getLoginInfo(userID, email);
        if(login == null){
            ErrorContext errorContext = new ErrorContext(null, "Login", "Login info not found by userID: "
                    + userID + " & email: " + email);
            ErrorMessage errorMessage = new ErrorMessage(Constants.AUTHENTICATE_SERVICE_0005,
                    Constants.AUTHENTICATE_SERVICE_0005_DESCRIPTION, errorContext);
            throw new CustomException(errorMessage);
        }
        return login;
    }

    @Override
    public Login getLoginInfoByResetToken(String resetToken) throws CustomException {
        Login login = dao.getLoginInfoByResetToken(resetToken);
        if(login == null){
            ErrorContext errorContext = new ErrorContext(null, "Login", "Login info not found by passwordResetToken: " + resetToken);
            ErrorMessage errorMessage = new ErrorMessage(Constants.AUTHENTICATE_SERVICE_0005,
                    Constants.AUTHENTICATE_SERVICE_0005_DESCRIPTION, errorContext);
            throw new CustomException(errorMessage);
        }
        return login;
    }
}
