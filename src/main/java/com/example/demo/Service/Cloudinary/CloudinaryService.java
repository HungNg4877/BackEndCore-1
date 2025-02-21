package com.example.demo.Service.Cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.demo.Common.AttributeConstant;
import com.example.demo.Common.ErrorCode;
import com.example.demo.Common.MediaType;
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
                    AttributeConstant.CLOUDINARY_PUBLIC_ID, id,
                    AttributeConstant.CLOUDINARY_FOLDER, folderName,
                    AttributeConstant.CLOUDINARY_RESOURCE_TYPE, mediaType.toString().toLowerCase(),
                    AttributeConstant.CLOUDINARY_OVERWRITE, true));
        }catch (IOException io){
            throw new BaseException(ErrorCode.FAILED);
        }
    }
    public String deleteFile(String publicId){
        try {
            return this.cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap()).toString();
        } catch (IOException e) {
            throw new BaseException(ErrorCode.FAILED);
        }
    }

}

