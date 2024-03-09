package com.gangdestrois.smartimmo.domain.document;

import com.gangdestrois.smartimmo.domain.property.port.PropertySpi;
import com.gangdestrois.smartimmo.domain.prospect.port.ProspectSpi;
import com.gangdestrois.smartimmo.domain.utils.Model;
import com.gangdestrois.smartimmo.domain.utils.ModelSpi;

import java.util.Optional;
import java.util.function.Function;

public enum OwnerType {
    PROPERTY((Long reference) -> findById(reference, DataSource.propertySpi)),
    PROSPECT((Long reference) -> findById(reference, DataSource.prospectSpi));
    private final Function<Long, ? extends Optional<? extends Model>> getOwner;

    public static class DataSource {
        private static PropertySpi propertySpi;
        private static ProspectSpi prospectSpi;

        public static void setProspectSpi(ProspectSpi prospectSpi) {
            DataSource.prospectSpi = prospectSpi;
        }

        public static void setPropertySpi(PropertySpi propertySpi) {
            DataSource.propertySpi = propertySpi;
        }
    }

    OwnerType(Function<Long, Optional<? extends Model>> getOwner) {
        this.getOwner = getOwner;
    }

    public Optional<? extends Model> getOwner(Long reference) {
        return getOwner.apply(reference);
    }

    private static Optional<? extends Model> findById(Long reference, ModelSpi<? extends Model> modelSpi) {
        return modelSpi.findById(reference);
    }
}
