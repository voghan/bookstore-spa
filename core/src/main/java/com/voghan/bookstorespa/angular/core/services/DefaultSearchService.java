package com.voghan.bookstorespa.angular.core.services;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceDescription;

@Component(service = SearchService.class)
@ServiceDescription("Bookstore Search Service")
public class DefaultSearchService implements SearchService {

    @Override
    public void searchContent(String searchTerm, int maxResults) {

    }
}
