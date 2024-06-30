package com.gangdestrois.smartimmo.domain.document.enums;

import com.gangdestrois.smartimmo.domain.document.model.File;
import com.gangdestrois.smartimmo.domain.document.model.Holder;
import com.gangdestrois.smartimmo.domain.document.port.DocumentSpi;
import com.gangdestrois.smartimmo.domain.document.port.HolderSpi;
import com.gangdestrois.smartimmo.domain.property.port.PropertySpi;
import com.gangdestrois.smartimmo.domain.prospect.port.ProspectSpi;
import com.gangdestrois.smartimmo.infrastructure.rest.error.NotFoundException;

import java.util.List;
import java.util.function.Function;

import static com.gangdestrois.smartimmo.infrastructure.rest.error.ExceptionEnum.HOLDER_NOT_FOUND;

public enum DocumentHolderType {
    PROPERTY((Long reference) -> findHolder(reference, DataSource.propertySpi),
            (Long reference) -> findDocuments(reference, DataSource.propertySpi)),
    PROSPECT((Long reference) -> findHolder(reference, DataSource.prospectSpi),
            (Long reference) -> findDocuments(reference, DataSource.prospectSpi));

    private final Function<Long, Holder> getHolder;
    private final Function<Long, List<File>> getDocuments;

    DocumentHolderType(Function<Long, Holder> getHolder, Function<Long, List<File>> getDocuments) {
        this.getHolder = getHolder;
        this.getDocuments = getDocuments;
    }

    public List<File> getDocuments(Long reference) {
        return this.getDocuments.apply(reference);
    }

    public Holder getHolder(Long reference) {
        return this.getHolder.apply(reference);
    }

    private static List<File> findDocuments(Long reference, HolderSpi<? extends Holder> modelSpi) {
        var holder = modelSpi.findById(reference).orElseThrow(
                () -> new NotFoundException(HOLDER_NOT_FOUND, String.format("Holder with id %d doesn't exists.", reference)));
        return holder.getFiles(DataSource.documentSpi);
    }

    private static Holder findHolder(Long reference, HolderSpi<? extends Holder> modelSpi) {
        return modelSpi.findById(reference).orElseThrow(
                () -> new NotFoundException(HOLDER_NOT_FOUND, String.format("Holder with id %d doesn't exists.", reference)));
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
