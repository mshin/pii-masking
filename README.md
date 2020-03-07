# pii-masking
generic pii-masking libraries

Core libraries:
masking-policy
  masking-annotation
  masking-service-api

The example-masking-policy and example-masking-util are just that, examples. For example-masking-policy, please create your own libraries comprised of your own policies that meet your masking needs. Regex patterns of a high complexity can be used to implement complex logic here. Basic masking logic can be quickly implemented with a policy as well. See class: com.github.mshin.masking.policy.MaskingPolicyConfiguration for more details.

The masking-service-api interfaces enables masking of whole models with fields annotated with @Pii. It uses reflection to get the annotations and mask the model field contents. A map is used to cache the object transversal to speed up the process after the 1st reflective call. Your own concrete implementations of all of the masking-service-api interfaces is possible. This could eliminate reflection completely if you know the types of objects you will be masking. There are examples in the tests of transversing a known model, and using no reflection. These are accomplished by implementing the masking-service-api interfaces.

The example-masking-util shows a way of masking field level content. This could be done in lieu of masking object level content.

For Object trees, there is unfinished implementation. All models passed into the MaskingService needs to have the Pii to be masked as field members of the Object. The Pii to be masked can't be in a nested Object within that model. To implement this functionality, please use a concrete Object (MaskingService<YourConcreteObject>) so that known object transversal can be done for nested Pii.

To Contact the code's author, contact MunChul Shin at mshin@redhat.com

Mar 2020