package com.github.mshin.masking.service.api.concrete.no.reflection;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.mshin.masking.service.api.MaskingHandler;
import com.github.mshin.masking.service.api.MaskingService;
import com.github.mshin.masking.service.api.PiiAnnotatedModel;
import com.github.mshin.masking.service.api.PiiAnnotatedObjectMaskingHandler;
import com.github.mshin.masking.service.impl.DefaultPiiAnnotationMaskingServiceImpl;

/**
 * @author mshin
 */
public class TestPiiAnnotatedModelMaskingServiceNoReflection {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestPiiAnnotatedModelMaskingServiceNoReflection.class);

    @Test
    public void testMaskedContentConcreteNoReflection() {

        MaskingService<PiiAnnotatedModel> service = new PiiAnnotatedModelMaskingServiceNoReflectionImpl();

        PiiAnnotatedModel msm = new PiiAnnotatedModel();

        LOGGER.info("Fields before: zipCode={} emailAddress={} userId={} nonPiiField={}", msm.getZipCode(),
                msm.getEmailAddress(), msm.getUserId(), msm.getNonPiiField());

        service.mask(msm);

        LOGGER.info("Fields after: zipCode={} emailAddress={} userId={} nonPiiField={}", msm.getZipCode(),
                msm.getEmailAddress(), msm.getUserId(), msm.getNonPiiField());

        LOGGER.info("MaskingHandler name instance map={}", MaskingHandler.NAME_INSTANCE_MAP);
        LOGGER.info("MaskingHandler class instance map={}", MaskingHandler.CLASS_INSTANCE_MAP);
        LOGGER.info("PiiAnnotatedObjectMaskingHandler fields map={}",
                PiiAnnotatedObjectMaskingHandler.PII_ANNOTATED_FIELDS_MAP);

        assertTrue("*****".equals(msm.getZipCode()));
        assertTrue("k****4".equals(msm.getUserId()));
        assertTrue("m****@redhat.com".equals(msm.getEmailAddress()));
        assertTrue("hi".equals(msm.getNonPiiField()));
    }

    @Test
    public void testMaskedContentInCollectionConcreteNoReflection() {

        MaskingService<Object> service = new DefaultPiiAnnotationMaskingServiceImpl();

        List<Object> list = new ArrayList<>();
        PiiAnnotatedModel msm1 = new PiiAnnotatedModel();
        PiiAnnotatedModel msm2 = new PiiAnnotatedModel();
        PiiAnnotatedModel msm3 = new PiiAnnotatedModel();
        list.add(msm1);
        list.add(msm2);
        list.add(msm3);

        LOGGER.info("Fields before(1): zipCode={} emailAddress={} userId={} nonPiiField={}", msm1.getZipCode(),
                msm1.getEmailAddress(), msm1.getUserId(), msm1.getNonPiiField());
        LOGGER.info("Fields before(2): zipCode={} emailAddress={} userId={} nonPiiField={}", msm2.getZipCode(),
                msm2.getEmailAddress(), msm2.getUserId(), msm2.getNonPiiField());
        LOGGER.info("Fields before(3): zipCode={} emailAddress={} userId={} nonPiiField={}", msm3.getZipCode(),
                msm3.getEmailAddress(), msm3.getUserId(), msm3.getNonPiiField());

        service.mask(list);

        LOGGER.info("Fields after(1): zipCode={} emailAddress={} userId={} nonPiiField={}", msm1.getZipCode(),
                msm1.getEmailAddress(), msm1.getUserId(), msm1.getNonPiiField());
        LOGGER.info("Fields after(2): zipCode={} emailAddress={} userId={} nonPiiField={}", msm2.getZipCode(),
                msm2.getEmailAddress(), msm2.getUserId(), msm2.getNonPiiField());
        LOGGER.info("Fields after(3): zipCode={} emailAddress={} userId={} nonPiiField={}", msm3.getZipCode(),
                msm3.getEmailAddress(), msm3.getUserId(), msm3.getNonPiiField());

        LOGGER.info("MaskingHandler name instance map={}", MaskingHandler.NAME_INSTANCE_MAP);
        LOGGER.info("MaskingHandler class instance map={}", MaskingHandler.CLASS_INSTANCE_MAP);
        LOGGER.info("PiiAnnotatedObjectMaskingHandler fields map={}",
                PiiAnnotatedObjectMaskingHandler.PII_ANNOTATED_FIELDS_MAP);

        assertTrue("*****".equals(msm1.getZipCode()));
        assertTrue("k****4".equals(msm1.getUserId()));
        assertTrue("m****@redhat.com".equals(msm1.getEmailAddress()));
        assertTrue("hi".equals(msm1.getNonPiiField()));

        assertTrue("*****".equals(msm2.getZipCode()));
        assertTrue("k****4".equals(msm2.getUserId()));
        assertTrue("m****@redhat.com".equals(msm2.getEmailAddress()));
        assertTrue("hi".equals(msm2.getNonPiiField()));

        assertTrue("*****".equals(msm3.getZipCode()));
        assertTrue("k****4".equals(msm3.getUserId()));
        assertTrue("m****@redhat.com".equals(msm3.getEmailAddress()));
        assertTrue("hi".equals(msm3.getNonPiiField()));
    }
}
