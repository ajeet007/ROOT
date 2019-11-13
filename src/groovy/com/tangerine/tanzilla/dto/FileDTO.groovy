package com.tangerine.tanzilla.dto

class FileDTO implements Comparable{
    String fileName
    String filePath
    boolean isReadable
    boolean isWritable
    boolean containsSubFolder

    @Override
    int compareTo(Object o) {
        return this.fileName.compareTo(((FileDTO)o).fileName)
    }
}
