package ru.kubsu.geocoder.dto;

import java.util.Objects;

public class RestApiError {
    private Integer status;
    private String Error;
    private String path;

    public Integer getStatus() {
        return status;
    }

    public String getError() {
        return Error;
    }

    public String getPath() {
        return path;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setError(String error) {
        Error = error;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestApiError that = (RestApiError) o;
        return Objects.equals(status, that.status) && Objects.equals(Error, that.Error) && Objects.equals(path, that.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, Error, path);
    }

    @Override
    public String toString() {
        return "RestApiError{" +
                "status=" + status +
                ", Error='" + Error + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
