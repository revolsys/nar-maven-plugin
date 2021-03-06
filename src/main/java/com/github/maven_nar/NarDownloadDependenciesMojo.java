/*
 * #%L
 * Native ARchive plugin for Maven
 * %%
 * Copyright (C) 2002 - 2014 NAR Maven Plugin developers.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.github.maven_nar;

import java.util.List;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.shared.artifact.filter.collection.ScopeFilter;

/**
 * List all the dependencies of the project and downloads the NAR files in local
 * maven repository if needed, this
 * includes the noarch and aol type NAR files.
 *
 * @author Mark Donszelmann
 */
@Mojo(name = "nar-download-dependencies", defaultPhase = LifecyclePhase.GENERATE_SOURCES, requiresProject = true,
  requiresDependencyResolution = ResolutionScope.TEST)
public class NarDownloadDependenciesMojo extends AbstractDependencyMojo {
  /**
   * List all the dependencies of the project.
   */
  @Override
  protected ScopeFilter getArtifactScopeFilter() {
    return new ScopeFilter( Artifact.SCOPE_TEST, null );
  }

  @Override
  public void narExecute() throws MojoFailureException, MojoExecutionException {
    // download the dependencies if needed in local maven repository.
    final List<AttachedNarArtifact> attachedNarArtifacts = getAttachedNarArtifacts();
    downloadAttachedNars(attachedNarArtifacts);
  }

}
