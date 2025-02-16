package com.uece.horas_complementares.service.qrCode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.uece.horas_complementares.security.TokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class qrCodeService {
    
    @Autowired
    private TokenService tokenService;
    
    public String gerarQRCode(Long idEvento, Long matriculaAluno) throws WriterException, IOException {

        String tokenDeValidacao = tokenService.generateTokenEvento(idEvento, matriculaAluno);
        System.out.println(tokenDeValidacao);
        // Configurações do QR Code
        int width = 250;
        int height = 250;
        String url = "http://localhost:3000/login" + "?eventoId=" +idEvento + "?alunoMatricula=" + matriculaAluno + "?tokenValidacao?=" + tokenDeValidacao;
        // Gerar o QR Code
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, width, height);

        // Converter o QR Code para uma imagem em base64
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
        String base64 = Base64.getEncoder().encodeToString(outputStream.toByteArray());

        // Retornar a imagem em base64
        return "data:image/png;base64," + base64 + "Token de validação: ," + tokenDeValidacao;
    }
}