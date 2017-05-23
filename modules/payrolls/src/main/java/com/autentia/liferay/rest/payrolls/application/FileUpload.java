package com.autentia.liferay.rest.payrolls.application;

import java.io.File;

class FileUpload {
    private final long repositoryId;
    private final long folderId;
    private final String sourceFileName;
    private final String mimeType;
    private final String title;
    private final String description;
    private final String changelog;
    private final File file;

    FileUpload(long repositoryId, long folderId, String sourceFileName, String mimeType, String title, String description, String changelog, File file) {
        this.repositoryId = repositoryId;
        this.folderId = folderId;
        this.sourceFileName = sourceFileName;
        this.mimeType = mimeType;
        this.title = title;
        this.description = description;
        this.changelog = changelog;
        this.file = file;
    }

    long getRepositoryId() {
        return repositoryId;
    }

    long getFolderId() {
        return folderId;
    }

    String getSourceFileName() {
        return sourceFileName;
    }

    String getMimeType() {
        return mimeType;
    }

    String getTitle() {
        return title;
    }

    String getDescription() {
        return description;
    }

    String getChangelog() {
        return changelog;
    }

    File getFile() {
        return file;
    }

}
