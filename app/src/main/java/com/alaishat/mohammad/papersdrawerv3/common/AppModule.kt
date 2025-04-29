package com.alaishat.mohammad.papersdrawerv3.common

import com.alaishat.mohammad.papersdrawerv3.features.draw_papers.DrawPapersUIState
import com.alaishat.mohammad.papersdrawerv3.features.draw_papers.ShowMessageEvent
import com.alaishat.mohammad.papersdrawerv3.features.possible_papers.PossiblePapersUIState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Mohammad Al-Aishat on Apr/29/2025.
 * PaperDrawer Project.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideShowMessageEventDelegate(): EventDelegate<ShowMessageEvent> = EventDelegate()

    @Provides
    fun provideDrawPapersUIStateDelegate(): StateDelegate<DrawPapersUIState> = StateDelegate()

    @Provides
    fun providePossiblePapersUIStateDelegate(): StateDelegate<PossiblePapersUIState> = StateDelegate()

}
