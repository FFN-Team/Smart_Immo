package com.gangdestrois.smartimmo.domain.document.enums;

import com.gangdestrois.smartimmo.domain.document.model.File;
import com.gangdestrois.smartimmo.domain.document.port.DocumentSpi;
import com.gangdestrois.smartimmo.domain.property.port.PropertySpi;
import com.gangdestrois.smartimmo.domain.prospect.port.ProspectSpi;
import com.gangdestrois.smartimmo.infrastructure.rest.error.ExceptionEnum;
import com.gangdestrois.smartimmo.infrastructure.rest.error.NotFoundException;

import java.util.List;
import java.util.function.Function;

public enum OwnerType {
    PROPERTY((Long reference) -> findPropertyDocumentHolder(reference, DataSource.propertySpi)),
    PROSPECT((Long reference) -> findProspectDocumentHolder(reference, DataSource.prospectSpi));
    private final Function<Long, List<File>> getDocuments;

    public static class DataSource {
        private static PropertySpi propertySpi;
        private static ProspectSpi prospectSpi;
        private static DocumentSpi documentSpi;

        public static void setProspectSpi(ProspectSpi prospectSpi) {
            DataSource.prospectSpi = prospectSpi;
        }

        public static void setPropertySpi(PropertySpi propertySpi) {
            DataSource.propertySpi = propertySpi;
        }

        public static void setDocumentSpi(DocumentSpi documentSpi) {
            DataSource.documentSpi = documentSpi;
        }
    }

    OwnerType(Function<Long, List<File>> getDocuments) {
        this.getDocuments = getDocuments;
    }

    public List<File> getDocuments(Long reference) {
        return this.getDocuments.apply(reference);
    }

    private static List<File> findPropertyDocumentHolder(Long reference, PropertySpi modelSpi) {
        var owner = modelSpi.findById(reference).orElseThrow(() ->
                new NotFoundException(ExceptionEnum.OWNER_NOT_FOUND, String.format("Owner with id %d doesn't exists.", reference)));
        return DataSource.documentSpi.getFileByOwner(owner);
    }

    private static List<File> findProspectDocumentHolder(Long reference, ProspectSpi modelSpi) {
        var owner = modelSpi.findById(reference).orElseThrow(() ->
                new NotFoundException(ExceptionEnum.OWNER_NOT_FOUND, String.format("Owner with id %d doesn't exists.", reference)));
        return DataSource.documentSpi.getFileByOwner(owner);
    }
}
