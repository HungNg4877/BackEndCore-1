package com.example.demo.Service.Cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.demo.Common.Cloudinary.CloudinaryConstant;
import com.example.demo.Common.Error.ErrorMessage;
import com.example.demo.Common.PostConstant.MediaType;
import com.example.demo.Exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryService {

    private final Cloudinary cloudinary;

    public Map upload(byte[] file, MediaType mediaType, String folderName, String id)  {
        try{
            return this.cloudinary.uploader().upload(file, ObjectUtils.asMap(
                    CloudinaryConstant.CLOUDINARY_PUBLIC_ID, id,
                    CloudinaryConstant.CLOUDINARY_FOLDER, folderName,
                    CloudinaryConstant.CLOUDINARY_RESOURCE_TYPE, mediaType.toString().toLowerCase(),
                    CloudinaryConstant.CLOUDINARY_OVERWRITE, true));
        }catch (IOException io){
            throw new BaseException(ErrorMessage.FAILED);
        }
    }
    public String deleteFile(String publicId){
        try {
            return this.cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap()).toString();
        } catch (IOException e) {
            throw new BaseException(ErrorMessage.FAILED);
        }
    }

}

