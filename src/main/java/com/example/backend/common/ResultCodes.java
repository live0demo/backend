package com.example.backend.common;

import org.springframework.http.HttpStatus;

public interface ResultCodes {
    ResultCode SUCCESS = new ResultCode(200, "success", HttpStatus.OK);
    ResultCode INTERNAL_SERVER_ERROR = new ResultCode(500, "error.systemError", HttpStatus.OK);
    ResultCode UNAUTHORIZED = new ResultCode(401, "error.unauthorized", HttpStatus.OK);
    ResultCode PERMISSION_DENIED = new ResultCode(403, "error.forbidden", HttpStatus.OK);
    ResultCode MISSING_PARAMETER = new ResultCode(400, "error.missingParameter", HttpStatus.OK);
    ResultCode INVALID_PARAMETER = new ResultCode(402, "error.invalidParameter", HttpStatus.OK);
    ResultCode COMMON_INVALID_PARAMETER = new ResultCode(400, "error.commonInvalidParameter", HttpStatus.OK);
    ResultCode TYPE_MISMATCH = new ResultCode(404, "error.typeMismatch", HttpStatus.OK);
    ResultCode NOT_FOUND = new ResultCode(405, "error.notFound", HttpStatus.OK);
    ResultCode BAD_REQUEST = new ResultCode(406, "error.badRequest", HttpStatus.OK);
    ResultCode NOT_SUPPORT_INVOICE_TYPE = new ResultCode(407, "error.notSupportInvoiceType", HttpStatus.OK);
    ResultCode MAX_LENGTH = new ResultCode(408, "error.maxLength", HttpStatus.OK);
    ResultCode MIN_LENGTH = new ResultCode(409, "error.minLength", HttpStatus.OK);
    ResultCode NOT_SUPPORT_MEDIATYPE = new ResultCode(410, "error.notSupportMediaType", HttpStatus.OK);
    ResultCode NOT_SUPPORT_METHOD = new ResultCode(411, "error.notSupportMethod", HttpStatus.OK);
    ResultCode STALE_OBJECT_CONFLICT = new ResultCode(412, "error.stateObjectConflict", HttpStatus.OK);
    ResultCode CANT_LOCK = new ResultCode(413, "error.cantLock", HttpStatus.OK);
    ResultCode TOO_MANY_REQUEST = new ResultCode(414, "error.tooManyRequest", HttpStatus.OK);
}
