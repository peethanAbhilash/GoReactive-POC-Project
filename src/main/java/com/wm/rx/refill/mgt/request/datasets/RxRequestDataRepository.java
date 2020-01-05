package com.wm.rx.refill.mgt.request.datasets;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * Note :  Not used in this version. In future we can store data in Mongo Reactive DB.
 */
public interface RxRequestDataRepository extends ReactiveMongoRepository<RxRequestData,String> {

}
