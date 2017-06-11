package ru.smedialink.eagleviewer.di;

import dagger.Module;
import dagger.Provides;
import ru.smedialink.eagleviewer.interfaces.InternetConnectionChecker;
import ru.smedialink.eagleviewer.interfaces.TokenContainer;
import ru.smedialink.eagleviewer.network.EagleViewStreamingApi;
import ru.smedialink.eagleviewer.network.EagleViewUploadPhotosApi;
import ru.smedialink.eagleviewer.network.EagleViewerApi;
import ru.smedialink.eagleviewer.network.NetworkService;
import ru.smedialink.eagleviewer.network.interceptors.AuthorizationInterceptor;
import ru.smedialink.eagleviewer.network.interceptors.NoInternetInterceptor;

@PerApplication
@Module
public class RestModule {

    @PerApplication
    @Provides
    AuthorizationInterceptor provideAuthorizationInterceptor(TokenContainer tokenContainer) {
        return new AuthorizationInterceptor(tokenContainer);
    }

    @PerApplication
    @Provides
    NoInternetInterceptor provideNoInternetInterceptor(InternetConnectionChecker internetConnectionChecker) {
        return new NoInternetInterceptor(internetConnectionChecker);
    }

    @PerApplication
    @Provides
    EagleViewerApi provideApi(NetworkService networkService) {
        return networkService.getApi();
    }

    @PerApplication
    @Provides
    EagleViewStreamingApi provideStreamingApi(NetworkService networkService) {
        return networkService.getStreamingApi();
    }

    @PerApplication
    @Provides
    EagleViewUploadPhotosApi providePhotosApi(NetworkService networkService) {
        return networkService.getEagleViewUploadPhotosApi();
    }


}
