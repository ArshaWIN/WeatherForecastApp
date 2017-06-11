package ru.smedialink.eagleviewer.di;


import android.content.Context;

import dagger.Component;
import ru.smedialink.eagleviewer.interactors.UserSessionInteractor;
import ru.smedialink.eagleviewer.interfaces.InternetConnectionChecker;
import ru.smedialink.eagleviewer.interfaces.InternetListener;
import ru.smedialink.eagleviewer.interfaces.PreferencesProvider;
import ru.smedialink.eagleviewer.managers.FileDownloadingManager;
import ru.smedialink.eagleviewer.managers.GeoJsonsDownloadingManager;
import ru.smedialink.eagleviewer.managers.PhotosManager;
import ru.smedialink.eagleviewer.managers.SynchronizeProgressManager;
import ru.smedialink.eagleviewer.managers.UserSessionManager;
import ru.smedialink.eagleviewer.network.EagleViewUploadPhotosApi;
import ru.smedialink.eagleviewer.network.EagleViewerApi;
import ru.smedialink.eagleviewer.network.PhotoRequestBodyFabric;
import ru.smedialink.eagleviewer.persistent.database.photos.PhotosRepository;
import ru.smedialink.eagleviewer.ui.interfaces.DialogShower;
import ru.smedialink.eagleviewer.ui.interfaces.MessageShower;
import ru.smedialink.eagleviewer.ui.widgets.LoadingIndicator;
import ru.smedialink.eagleviewer.utils.GeoUpdateTimer;
import ru.smedialink.eagleviewer.utils.PermissionsChecker;
import ru.smedialink.eagleviewer.utils.geofeatures.GeoFeaturesRepository;

@PerBaseActivity
@Component(dependencies = {AppComponent.class}, modules = {ActivityModule.class})
public interface ActivityComponent {

    Context getContext();

    MessageShower getMessageShower();

    EagleViewerApi getEagleViewerApi();

    EagleViewUploadPhotosApi getEagleViewUploadPhotosApi();

    UserSessionManager getUserSessionManager();

    UserSessionInteractor getUserSessionInteractor();

    InternetConnectionChecker provideInternetConnectionChecker();

    InternetListener provideInternetListener();

    DialogShower provideDialogShower();

    PreferencesProvider providePreferencesProvider();

    PermissionsChecker providePermissionsChecker();

    LoadingIndicator provideLoadingIndicator();

    FileDownloadingManager provideFileDownloadingManager();

    GeoJsonsDownloadingManager provideGeoJsonsDownloadingManager();

    GeoFeaturesRepository provideGeoFeaturesPointsList();

    PhotosRepository providePhotosRepository();

    PhotosManager providePhotosManager();

    GeoUpdateTimer provideGeoUpdateTime();

    SynchronizeProgressManager provideSynchronizeProgressManager();

    PhotoRequestBodyFabric providePhotoRequestBodyFabric();

}
