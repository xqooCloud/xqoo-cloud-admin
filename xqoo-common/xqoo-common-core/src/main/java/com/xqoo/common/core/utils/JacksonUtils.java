/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xqoo.common.core.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.xqoo.common.core.exception.SystemException;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

/**
 * Json utils implement by Jackson.
 *
 * @author <a href="mailto:liaochuntao@live.com">liaochuntao</a>
 */
public final class JacksonUtils {

    static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setSerializationInclusion(Include.NON_NULL);
    }

    /**
     * Object to json string.
     *
     * @param obj obj
     * @return json string
     * @throws SystemException if transfer failed
     */
    public static String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new SystemException("对象转为json字符串错误", obj.toString(), e);
        }
    }

    /**
     * Object to json string.
     *
     * @param obj obj
     * @return json string
     * @throws SystemException if transfer failed
     */
    public static String toJsonStr(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new SystemException("对象转为json字符串错误", obj.toString(), e);
        }
    }

    /**
     * Object to json string byte array.
     *
     * @param obj obj
     * @return json string byte array
     * @throws SystemException if transfer failed
     */
    public static byte[] toJsonBytes(Object obj) {
        try {
            return ByteUtils.toBytes(mapper.writeValueAsString(obj));
        } catch (JsonProcessingException e) {
            throw new SystemException("对象转为byte数据错误", e);
        }
    }

    /**
     * Json string deserialize to Object.
     *
     * @param json json string
     * @param cls  class of object
     * @param <T>  General type
     * @return object
     * @throws SystemException if deserialize failed
     */
    public static <T> T toObj(byte[] json, Class<T> cls) {
        try {
            return toObj(StringUtils.newStringForUtf8(json), cls);
        } catch (Exception e) {
            throw new SystemException("byte数据转换为对象错误", e);
        }
    }
    /**
     * Json string deserialize to Object.
     *
     * @param json json string
     * @param cls  {@link Type} of object
     * @param <T>  General type
     * @return object
     * @throws SystemException if deserialize failed
     */
    public static <T> T toObj(byte[] json, Type cls) {
        try {
            return toObj(StringUtils.newStringForUtf8(json), cls);
        } catch (Exception e) {
            throw new SystemException("byte数据转换为对象错误", e);
        }
    }

    /**
     * Json string deserialize to Object.
     *
     * @param inputStream json string input stream
     * @param cls         class of object
     * @param <T>         General type
     * @return object
     * @throws SystemException if deserialize failed
     */
    public static <T> T toObj(InputStream inputStream, Class<T> cls) {
        try {
            return mapper.readValue(inputStream, cls);
        } catch (IOException e) {
            throw new SystemException("文件流转换为对象错误", e);
        }
    }

    /**
     * Json string deserialize to Object.
     *
     * @param json          json string byte array
     * @param typeReference {@link TypeReference} of object
     * @param <T>           General type
     * @return object
     * @throws SystemException if deserialize failed
     */
    public static <T> T toObj(byte[] json, TypeReference<T> typeReference) {
        try {
            return toObj(StringUtils.newStringForUtf8(json), typeReference);
        } catch (Exception e) {
            throw new SystemException("byte数据转换为对象错误", e);
        }
    }

    /**
     * Json string deserialize to Object.
     *
     * @param jsonNode          JsonNode
     * @param typeReference {@link TypeReference} of object
     * @param <T>           General type
     * @return object
     * @throws SystemException if deserialize failed
     */
    public static <T> T toObj(JsonNode jsonNode, TypeReference<T> typeReference) {
        try {
            return mapper.convertValue(jsonNode, typeReference);
        } catch (Exception e) {
            throw new SystemException("byte数据转换为对象错误", e);
        }
    }

    /**
     * Json string deserialize to Object.
     *
     * @param json json string
     * @param cls  class of object
     * @param <T>  General type
     * @return object
     * @throws SystemException if deserialize failed
     */
    public static <T> T toObj(String json, Class<T> cls) {
        try {
            return mapper.readValue(json, cls);
        } catch (IOException e) {
            throw new SystemException("json字符串转换为对象出错", e);
        }
    }

    /**
     * Json string deserialize to Object.
     *
     * @param json json string
     * @param type {@link Type} of object
     * @param <T>  General type
     * @return object
     * @throws SystemException if deserialize failed
     */
    public static <T> T toObj(String json, Type type) {
        try {
            return mapper.readValue(json, mapper.constructType(type));
        } catch (IOException e) {
            throw new SystemException("json字符串转换为对象出错", e);
        }
    }

    /**
     * Json string deserialize to Object.
     *
     * @param json          json string
     * @param typeReference {@link TypeReference} of object
     * @param <T>           General type
     * @return object
     * @throws SystemException if deserialize failed
     */
    public static <T> T toObj(String json, TypeReference<T> typeReference) {
        try {
            return mapper.readValue(json, typeReference);
        } catch (IOException e) {
            throw new SystemException("json字符串转换为对象出错", e);
        }
    }

    /**
     * Json string deserialize to Object.
     *
     * @param inputStream json string input stream
     * @param type        {@link Type} of object
     * @param <T>         General type
     * @return object
     * @throws SystemException if deserialize failed
     */
    public static <T> T toObj(InputStream inputStream, Type type) {
        try {
            return mapper.readValue(inputStream, mapper.constructType(type));
        } catch (IOException e) {
            throw new SystemException("文件流转换为对象出错", e);
        }
    }

    /**
     * Json string deserialize to Jackson {@link JsonNode}.
     *
     * @param json json string
     * @return {@link JsonNode}
     * @throws SystemException if deserialize failed
     */
    public static JsonNode toObj(String json) {
        try {
            return mapper.readTree(json);
        } catch (IOException e) {
            throw new SystemException("json字符串转换为JSON对象错误", e);
        }
    }

    /**
     * Register sub type for child class.
     *
     * @param clz  child class
     * @param type type name of child class
     */
    public static void registerSubtype(Class<?> clz, String type) {
        mapper.registerSubtypes(new NamedType(clz, type));
    }

    /**
     * Create a new Jackson {@link ObjectNode}.
     *
     * @return {@link ObjectNode}
     */
    public static ObjectNode createObjectNode() {
        return mapper.createObjectNode();
    }

    /**
     * Create a new empty Jackson {@link ObjectNode}.
     *
     * @return {@link ObjectNode}
     */
    public static ObjectNode createEmptyJsonNode() {
        return new ObjectNode(mapper.getNodeFactory());
    }

    /**
     * Create a new empty Jackson {@link ArrayNode}.
     *
     * @return {@link ArrayNode}
     */
    public static ArrayNode createArrayNode() {
        return mapper.createArrayNode();
    }

    /**
     * Create a new empty Jackson {@link ArrayNode}.
     *
     * @return {@link ArrayNode}
     */
    public static ArrayNode createEmptyArrayNode() {
        return new ArrayNode(mapper.getNodeFactory());
    }

    /**
     * Parse object to Jackson {@link JsonNode}.
     *
     * @param obj object
     * @return {@link JsonNode}
     */
    public static JsonNode transferToJsonNode(Object obj) {
        try {
            return mapper.valueToTree(obj);
        }catch (IllegalArgumentException e){
            throw new SystemException("对象转换为jsonNode错误", e);
        }
    }

    /**
     * construct java type -> Jackson Java Type.
     *
     * @param type java type
     * @return JavaType {@link JavaType}
     */
    public static JavaType constructJavaType(Type type) {
        return mapper.constructType(type);
    }

    /**
     * 对json字符串格式化输出
     */
    public static String formatJson(String jsonStr) {
        if (null == jsonStr || "".equals(jsonStr)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        char last = '\0';
        char current = '\0';
        int indent = 0;
        for (int i = 0; i < jsonStr.length(); i++) {
            last = current;
            current = jsonStr.charAt(i);
            switch (current) {
                case '{':
                case '[':
                    sb.append(current);
                    sb.append('\n');
                    indent++;
                    addIndentBlank(sb, indent);
                    break;
                case '}':
                case ']':
                    sb.append('\n');
                    indent--;
                    addIndentBlank(sb, indent);
                    sb.append(current);
                    break;
                case ',':
                    sb.append(current);
                    if (last != '\\') {
                        sb.append('\n');
                        addIndentBlank(sb, indent);
                    }
                    break;
                default:
                    sb.append(current);
            }
        }

        return sb.toString();
    }

    /**
     * 添加space
     */
    private static void addIndentBlank(StringBuilder sb, int indent) {
        for (int i = 0; i < indent; i++) {
            sb.append('\t');
        }
    }
}
