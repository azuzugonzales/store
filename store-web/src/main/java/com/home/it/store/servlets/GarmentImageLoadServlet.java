package com.home.it.store.servlets;

import com.home.it.store.controllers.GarmentController;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static com.home.it.store.servlets.ServletConstants.*;

public class GarmentImageLoadServlet extends SpringContextLoaderAbstractServlet {
    @Autowired
    private GarmentController controller;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(IMAGE_GIF);
        int id = Integer.valueOf(req.getParameter(ID));
        File image = new File(controller.getGarment(id).getPicturePath());
        try (ServletOutputStream outputStream = resp.getOutputStream();
             BufferedInputStream bis =
                new BufferedInputStream(new FileInputStream(image))) {
            int count;
            byte[] buffer = new byte[1024 * 8];
            while ((count = bis.read(buffer)) != -1) {
                outputStream.write(buffer, 0, count);
            }
            outputStream.flush();
        } catch (IOException e) {
            /*NOP*/
        }
    }
}
