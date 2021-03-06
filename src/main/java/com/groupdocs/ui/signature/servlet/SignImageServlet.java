package com.groupdocs.ui.signature.servlet;

import com.google.gson.Gson;
import com.groupdocs.ui.signature.model.request.SignDocumentRequest;
import com.groupdocs.ui.signature.model.web.SignedDocumentEntity;
import com.groupdocs.ui.signature.service.SignatureService;
import com.groupdocs.ui.signature.util.JsonResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signature/signImage")
public class SignImageServlet extends CORSServlet {
    private static final Logger logger = LoggerFactory.getLogger(SignImageServlet.class);

    @Inject
    private SignatureService signatureService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setControlHeader(resp);

        SignDocumentRequest signDocumentRequest = new Gson().fromJson(req.getReader(), SignDocumentRequest.class);

        SignedDocumentEntity signedDocumentEntity = signatureService.signImage(signDocumentRequest);

        JsonResponseUtils.writeJson(resp, signedDocumentEntity);
    }
}
