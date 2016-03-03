package br.com.cit.contacts.api.service.validation;

import br.com.cit.contacts.api.exception.ValidationException;
import br.com.cit.contacts.model.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Validation<T extends Entity> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Validation.class);

    public void validate(T entity) throws ValidationException {
        if (entity == null) {
            throw new ValidationException("Entity cannot be null!");
        }

        LOGGER.debug("Validate entity: [{}]!", entity.getClass().getName());
        if (!shouldProcessEntity(entity)) {
            throw new ValidationException(getMessageValidationFailed());
        }

    }

    public abstract Boolean shouldProcessEntity(T entity);

    public abstract String getMessageValidationFailed();
}
