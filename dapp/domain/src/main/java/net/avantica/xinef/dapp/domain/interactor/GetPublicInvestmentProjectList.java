/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.avantica.xinef.dapp.domain.interactor;

import net.avantica.xinef.dapp.domain.executor.PostExecutionThread;
import net.avantica.xinef.dapp.domain.executor.ThreadExecutor;
import net.avantica.xinef.dapp.domain.repository.PublicInvestmentProjectRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving a collection of all {@link net.avantica.xinef.dapp.domain.entity.PublicInvestmentProject}.
 */
public class GetPublicInvestmentProjectList extends UseCase {

    private final String departmentName;
    private final PublicInvestmentProjectRepository publicInvestmentProjectRepository;

    @Inject
    public GetPublicInvestmentProjectList(String departmentName, PublicInvestmentProjectRepository publicInvestmentProjectRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.departmentName = departmentName;
        this.publicInvestmentProjectRepository = publicInvestmentProjectRepository;
    }

    @Override
    public Observable buildUseCaseObservable() {
        return this.publicInvestmentProjectRepository.publicInvestmentProjects(departmentName);
    }
}
