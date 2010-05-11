package org.onebusaway.container.spring;

import java.io.IOException;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.config.Configuration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

public class EhCacheManagerFactoryBean implements FactoryBean,
    InitializingBean, DisposableBean {

  protected final Log logger = LogFactory.getLog(getClass());

  private Resource configLocation;

  private Configuration configuration;

  private String cacheManagerName;

  private CacheManager cacheManager;

  public void setConfigLocation(Resource configLocation) {
    this.configLocation = configLocation;
  }

  public void setConfiguration(Configuration configuration) {
    this.configuration = configuration;
  }

  /**
   * Set the name of the EHCache CacheManager (if a specific name is desired).
   * 
   * @see net.sf.ehcache.CacheManager#setName(String)
   */
  public void setCacheManagerName(String cacheManagerName) {
    this.cacheManagerName = cacheManagerName;
  }

  public void afterPropertiesSet() throws IOException, CacheException {
    logger.info("Initializing EHCache CacheManager");

    // Independent CacheManager instance (the default).
    if (this.configuration != null) {
      this.cacheManager = new CacheManager(this.configuration);
    } else if (this.configLocation != null) {
      this.cacheManager = new CacheManager(this.configLocation.getInputStream());
    } else {
      this.cacheManager = new CacheManager();
    }

    if (this.cacheManagerName != null) {
      this.cacheManager.setName(this.cacheManagerName);
    }
  }

  public Object getObject() {
    return this.cacheManager;
  }

  public Class<?> getObjectType() {
    return (this.cacheManager != null ? this.cacheManager.getClass()
        : CacheManager.class);
  }

  public boolean isSingleton() {
    return true;
  }

  public void destroy() {
    logger.info("Shutting down EHCache CacheManager");
    this.cacheManager.shutdown();
  }

}