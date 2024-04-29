package com.gangdestrois.smartimmo.domain.document.enums;

import com.gangdestrois.smartimmo.domain.document.model.File;
import com.gangdestrois.smartimmo.domain.document.port.DocumentSpi;
import com.gangdestrois.smartimmo.domain.document.util.Holder;
import com.gangdestrois.smartimmo.domain.document.util.HolderSpi;
import com.gangdestrois.smartimmo.domain.property.port.PropertySpi;
import com.gangdestrois.smartimmo.domain.prospect.port.ProspectSpi;
import com.gangdestrois.smartimmo.infrastructure.rest.error.NotFoundException;

import java.util.List;
import java.util.function.Function;

import static com.gangdestrois.smartimmo.infrastructure.rest.error.ExceptionEnum.OWNER_NOT_FOUND;

public enum DocumentHolderType {
    PROPERTY((Long reference) -> findDocuments(reference, DataSource.propertySpi)),
    PROSPECT((Long reference) -> findDocuments(reference, DataSource.prospectSpi));
    private final Function<Long, List<File>> getDocuments;

    DocumentHolderType(Function<Long, List<File>> getDocuments) {
        this.getDocuments = getDocuments;
    }

    public List<File> getDocuments(Long reference) {
        return this.getDocuments.apply(reference);
    }

    private static List<File> findDocuments(Long reference, HolderSpi<? extends Holder> modelSpi) {
        var owner = modelSpi.findById(reference).orElseThrow(
                () -> new NotFoundException(OWNER_NOT_FOUND, String.format("Owner with id %d doesn't exists.", reference)));
        return DataSource.documentSpi.getFileByDocumentHolder(owner);
    }

    public static class DataSource {
        private static HolderSpi<? extends Holder> propertySpi;
        private static HolderSpi<? extends Holder> prospectSpi;
        private static DocumentSpi documentSpi;

        public static void setPropertySpi(PropertySpi propertySpi) {
            DataSource.propertySpi = propertySpi;
        }

        public static void setProspectSpi(ProspectSpi prospectSpi) {
            DataSource.prospectSpi = prospectSpi;
        }

        public static void setDocumentSpi(DocumentSpi documentSpi) {
            DataSource.documentSpi = documentSpi;
        }
    }
}
