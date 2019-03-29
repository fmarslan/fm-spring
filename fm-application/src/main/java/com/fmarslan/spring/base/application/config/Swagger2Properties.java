/**
 * 
 * MIT License
 * 
 * Copyright (c) 2017 Think Wong
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 * 
 */

package com.fmarslan.spring.base.application.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Swagger2 properties.
 *
 * @author Think Wong
 * 
 * edited by fmarslan
 */
@ConfigurationProperties(prefix = "spring.swagger")
public class Swagger2Properties {

  public static class ApiInfo {

    public static class Contact {
      private String name = "";
      private String url = "";
      private String email = "";

      public String getName() {
        return name;
      }

      public void setName(String name) {
        this.name = name;
      }

      public String getUrl() {
        return url;
      }

      public void setUrl(String url) {
        this.url = url;
      }

      public String getEmail() {
        return email;
      }

      public void setEmail(String email) {
        this.email = email;
      }
    }

    private String title = "";
    private String description = "";
    private String termsOfServiceUrl = "";
    private Contact contact = new Contact();
    private String license = "";
    private String licenseUrl = "";
    private String version = "";

    public String getTitle() {
      return title;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    public String getDescription() {
      return description;
    }

    public void setDescription(String description) {
      this.description = description;
    }

    public String getTermsOfServiceUrl() {
      return termsOfServiceUrl;
    }

    public void setTermsOfServiceUrl(String termsOfServiceUrl) {
      this.termsOfServiceUrl = termsOfServiceUrl;
    }

    public Contact getContact() {
      return contact;
    }

    public void setContact(Contact contact) {
      this.contact = contact;
    }

    public String getLicense() {
      return license;
    }

    public void setLicense(String license) {
      this.license = license;
    }

    public String getLicenseUrl() {
      return licenseUrl;
    }

    public void setLicenseUrl(String licenseUrl) {
      this.licenseUrl = licenseUrl;
    }

    public String getVersion() {
      return version;
    }

    public void setVersion(String version) {
      this.version = version;
    }
  }

  public static class Parameter {
    private String name = "";
    private String description = "";
    private String defaultValue = "";
    private Boolean required = true;
    private String modelRef = "";
    private String paramType = "";

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getDescription() {
      return description;
    }

    public void setDescription(String description) {
      this.description = description;
    }

    public String getDefaultValue() {
      return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
      this.defaultValue = defaultValue;
    }

    public Boolean getRequired() {
      return required;
    }

    public void setRequired(Boolean required) {
      this.required = required;
    }

    public String getModelRef() {
      return modelRef;
    }

    public void setModelRef(String modelRef) {
      this.modelRef = modelRef;
    }

    public String getParamType() {
      return paramType;
    }

    public void setParamType(String paramType) {
      this.paramType = paramType;
    }


  }

  private ApiInfo apiInfo = new ApiInfo();
  private List<String> basePackage = new ArrayList<>();
  private List<String> basePath = new ArrayList<>();
  private List<String> excludePath = new ArrayList<>();
  private List<Parameter> globalOperationParameter = new ArrayList<>();

  public ApiInfo getApiInfo() {
    return apiInfo;
  }

  public void setApiInfo(ApiInfo apiInfo) {
    this.apiInfo = apiInfo;
  }

  public List<String> getBasePackage() {
    return basePackage;
  }

  public void setBasePackage(List<String> basePackage) {
    this.basePackage = basePackage;
  }

  public List<String> getBasePath() {
    return basePath;
  }

  public void setBasePath(List<String> basePath) {
    this.basePath = basePath;
  }

  public List<String> getExcludePath() {
    return excludePath;
  }

  public void setExcludePath(List<String> excludePath) {
    this.excludePath = excludePath;
  }

  public List<Parameter> getGlobalOperationParameter() {
    return globalOperationParameter;
  }

  public void setGlobalOperationParameter(List<Parameter> globalOperationParameter) {
    this.globalOperationParameter = globalOperationParameter;
  }

}
