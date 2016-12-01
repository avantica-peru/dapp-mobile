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
package net.avantica.xinef.dapp.di.modules;

import net.avantica.xinef.dapp.di.PerActivity;
import net.avantica.xinef.dapp.domain.executor.PostExecutionThread;
import net.avantica.xinef.dapp.domain.executor.ThreadExecutor;
import net.avantica.xinef.dapp.domain.interactor.GetPublicInvestmentProjectDetails;
import net.avantica.xinef.dapp.domain.interactor.GetPublicInvestmentProjectList;
import net.avantica.xinef.dapp.domain.interactor.UseCase;
import net.avantica.xinef.dapp.domain.repository.PublicInvestmentProjectRepository;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides user related collaborators.
 */
@Module
public class PublicInvestmentProjectModule {

    private String userId = "";

    public PublicInvestmentProjectModule() {
    }

    public PublicInvestmentProjectModule(String userId) {
        this.userId = userId;
    }

    @Provides
    @PerActivity
    @Named("publicInvestmentProjectList")
    UseCase provideGetUserListUseCase(
            GetPublicInvestmentProjectList getUserList) {
        return getUserList;
    }

    @Provides
    @PerActivity
    @Named("userDetails")
    UseCase provideGetUserDetailsUseCase(
            PublicInvestmentProjectRepository userRepository, ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread) {
        return new GetPublicInvestmentProjectDetails(userId, userRepository, threadExecutor, postExecutionThread);
    }
}