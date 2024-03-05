package com.gangdestrois.smartimmo.domain.document.model;

import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;

import java.util.Optional;

public class File extends DocumentImplementation {
    private Prospect owner;

    protected File(FileBuilder builder) {
        super(builder);
        this.owner = builder.owner;
    }

    public Optional<Prospect> getOwner() {
        return Optional.ofNullable(owner);
    }

    public void setOwner(Prospect owner) {
        this.owner = owner;
    }

    public static class FileBuilder extends DocumentImplementation.DocumentImplementationBuilder {
        private Prospect owner;

        public FileBuilder owner(Prospect owner) {
            this.owner = owner;
            return self();
        }

        @Override
        public File build() {
            return new File(this);
        }

        @Override
        protected FileBuilder self() {
            return this;
        }
    }
}
